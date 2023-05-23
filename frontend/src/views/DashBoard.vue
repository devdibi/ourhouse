<template>
  <div style="width: 100%; height: 1100px; display: flex; margin-top: 30px">
    <!-- 좌측 메뉴 영역 -->
    <div style="margin-left: 20px; width: 300px">
      <the-menu></the-menu>
    </div>
    <div style="display: block; padding: 0">
      <!-- 우측 대시보드 영역 -->
      <div style="margin-left: 30px; height: 40%; display: flex">
        <!-- LineChart -->
        <!-- 해당지역 연도별 평균 거래금액 추세 linechart  -->
        <!-- [select avg(price) from house_deat_test where dong_code = "" group by year] 2015 ~ 2022-->
        <div class="content-area" id="average">
          <line-chart class="chart-area" :year="line_year" :average="line_average" :dong="dong" v-if="loaded"></line-chart>
        </div>

        <!-- BarChart 스위칭 가능하도록 -->
        <!-- 
          해당지역 연도별 총 거래량 : 연도별 거래량의 차이를 확인할 수 있다.
          [select count(deal_code) from house_deal where dong_code = "" group by year]
        -->
        <!-- 
          해당지역 월별 총 거래량 : 2015 ~ 2022년까지 매월 거래량의 차이를 확인하여 연 중 어느 시점에 집을 보는것이 좋을지 판단하는 지표
          select count(deal_code) from house_deal where dong_code = "" group by month 
        -->
        <div class="content-area" id="amount" style="margin-left: 70px">
          <bar-chart class="chart-area" :year="bar_year" :year_data="bar_year_data" :month="bar_month" :month_data="bar_month_data" :dong="dong" v-if="loaded"></bar-chart>
        </div>

        <!-- Pie Chart 스위칭 가능하도록 -->
        <!-- 
          해당지역 연령별 관심 수 
          [select count(*) from search s left join user u on s.email = u.eamil where group by u.age_group ]
        -->
        <!-- 해당지역 성별 관심 수 pie chart
          [select count(*) from search s left join user u on s.email = u.eamil where group by u.gender ] 
        -->
        <div class="content-area" id="pie" style="margin-left: 70px">
          <pie-chart class="chart-area" v-if="loaded"></pie-chart>
        </div>
      </div>

      <!-- 우측 하단 -->
      <div style="height: 60%; display: flex">
        <!-- 
          해당지역 중심 주변 평균 거래금액 비교 지도
          해당지역의 시군구에 속하는 지역 모두 출력 
          단, 지도의 중심은 관심지역 -->
        <!-- polygon을 이용하여 event 생성 => dongcode 전달로 지도검색 수행 -->
        <div class="content-area" id="map" style="margin-left: 30px; margin-top: 50px; padding: 0">
          <map-chart class="chart-area" v-if="loaded" :areas="map_data" :dong="dong"></map-chart>
        </div>

        <!-- 해당 지역에서 거래량이 가장 많은 순서 건물 순위 10개 -->
        <!-- 해당 지역에서 거래 금액이 가장 큰 거래 순위 10개 -->
        <div class="content-area" id="list" style="margin-left: 30px; margin-top: 50px">hello</div>
      </div>
    </div>
  </div>
</template>

<script>
import TheMenu from "@/components/dashboard/TheMenu.vue";
import LineChart from "@/components/dashboard/LineChart.vue";
import BarChart from "@/components/dashboard/BarChart.vue";
import PieChart from "@/components/dashboard/PieChart.vue";
import MapChart from "@/components/dashboard/MapChart.vue";

import { average } from "@/api/dashboard.js";

export default {
  name: "DashBoard",
  components: { TheMenu, LineChart, BarChart, PieChart, MapChart },
  data() {
    return {
      dong_code: "5011010500", // dong code만 넘겨주면 세개의 시각화가 완성됨, 연령대 성별은 데이터가 없어서 추후 고민
      dong: "",
      line_year: [],
      line_average: [],
      bar_year: [],
      bar_year_data: [],
      bar_month: [],
      bar_month_data: [],
      map_data: [[]],
      loaded: false,
    };
  },
  created() {},
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      average(
        `${this.dong_code}`,
        (response) => {
          // geo 좌표 배열로 생성
          // console.log(response.data.geoList[0].geo.split("[").join().split("]").join().replace(/,, ,/g, "]|[").replace(",,,,", "[").replace(",,,,", "]").split("|"));
          this.map_data = response.data.geoList[0].geo.split("[").join().split("]").join().replace(/,, ,/g, "]|[").replace(",,,,", "[").replace(",,,,", "]").split("|");
          console.log(this.map_data);
          // line Chart
          const line = response.data.averageList;
          this.dong = line[0].name;
          console.log(line);
          let len = line.length;
          for (var i = 0; i < len; i++) {
            this.line_year.push(line[i].year);
            this.line_average.push(line[i].average);
          }

          // bar Chart
          const yearList = response.data.yearAmountList;
          console.log(yearList);
          len = yearList.length;
          for (i = 0; i < len; i++) {
            this.bar_year.push(yearList[i].year);
            this.bar_year_data.push(yearList[i].count);
          }

          const monthList = response.data.monthAmountList;
          console.log(monthList);
          len = monthList.length;
          for (i = 0; i < len; i++) {
            this.bar_month.push(monthList[i].month);
            this.bar_month_data.push(monthList[i].count);
          }

          this.loaded = true;
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>

<style scoped>
#average {
  width: 650px;
  height: auto;
  max-width: 700px;
  max-height: 500px;
  background: rgba(249, 247, 247, 0.7);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}

#amount {
  width: 650px;
  height: auto;
  max-width: 700px;
  max-height: 500px;
  background: rgba(247, 247, 247, 0.7);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}

#pie {
  width: 650px;
  height: auto;
  max-height: 500px;
  background: rgba(247, 247, 247, 0.7);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}

#map {
  width: 60%;
  height: auto;
  background: rgba(247, 247, 247, 0.7);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}
#list {
  width: 40%;
  height: auto;
  background: rgba(247, 247, 247, 0.7);
  box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 5px;
}
.content-area {
  padding: 10px;
  padding-top: 50px;
}

@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translate3d(0, 5%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}

.content-area {
  position: relative;
  animation: fadeInUp 4s;
}

.chart-area {
  position: relative;
  animation: fadeInUp 2s;
}
</style>
