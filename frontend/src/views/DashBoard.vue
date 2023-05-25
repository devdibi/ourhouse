<template>
	<div style="width: 100%; height: 1100px; display: flex; margin-top: 30px">
		<!-- 좌측 메뉴 영역 -->
		<div style="margin-left: 20px; width: 300px; height: 100%">
			<the-menu :user="user"></the-menu>
		</div>
		<!-- 중간 그래프 영역 -->
		<div style="display: block; padding: 0; height: 100%" v-if="loaded">
			<!-- LineChart -->
			<!-- 해당지역 연도별 평균 거래금액 추세 linechart  -->
			<!-- [select avg(price) from house_deat_test where dong_code = "" group by year] 2015 ~ 2022-->
			<div class="content-area" id="average" style="padding-top: 20px; margin-bottom: 20px">
				<line-chart
					class="chart-area"
					:year="line_year"
					:average="line_average"
					:dong="dong"
				></line-chart>
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
			<div
				class="content-area"
				id="amount"
				style="padding: 0px; margin-bottom: 20px; margin-top: 20px"
			>
				<bar-chart
					class="chart-area"
					:year="bar_year"
					:year_data="bar_year_data"
					:month="bar_month"
					:month_data="bar_month_data"
					:dong="dong"
					v-if="loaded"
				></bar-chart>
			</div>

			<!-- Pie Chart 스위칭 가능하도록 -->
			<!-- 
          해당지역 연령별 관심 수 
          [select count(*) from search s left join user u on s.email = u.eamil where group by u.age_group ]
        -->
			<!-- 해당지역 성별 관심 수 pie chart
          [select count(*) from search s left join user u on s.email = u.eamil where group by u.gender ] 
        -->
			<div class="content-area" id="pie" style="padding: 0px; margin-top: 20px">
				<pie-chart
					class="chart-area"
					v-if="loaded && this.age.length != 0"
					:age="age"
					:age_data="age_data"
					:gender="gender"
					:gender_data="gender_data"
				></pie-chart>
				<div style="width: 100%; height: 250px; text-align: center; vertical-align: middle" v-else>
					정보가 없습니다.
				</div>
			</div>

			<!-- 우측 하단 -->
		</div>
		<!-- dn -->
		<div style="width: 1600px; height: 100%; margin-left: 50px" v-if="loaded">
			<!-- 
          해당지역 중심 주변 평균 거래금액 비교 지도
          해당지역의 시군구에 속하는 지역 모두 출력 
          단, 지도의 중심은 관심지역 -->
			<!-- polygon을 이용하여 event 생성 => dongcode 전달로 지도검색 수행 -->
			<div class="content-area" id="map" style="padding: 0">
				<map-chart
					class="chart-area"
					v-if="loaded"
					:areas="map_data"
					:dong="dong"
					:c_lng="c_lng"
					:c_lat="c_lat"
				></map-chart>
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
import { getUserInfo } from "@/api/user";

import { mapGetters } from "vuex";

export default {
	name: "DashBoard",
	components: { TheMenu, LineChart, BarChart, PieChart, MapChart },
	computed: {
		...mapGetters({
			token: "getAccessToken",
		}),
	},
	data() {
		return {
			dong_code: "1162010200", // dong code만 넘겨주면 세개의 시각화가 완성됨, 연령대 성별은 데이터가 없어서 추후 고민
			dong: "",
			line_year: [],
			line_average: [],
			bar_year: [],
			bar_year_data: [],
			bar_month: [],
			bar_month_data: [],
			map_data: [[]],
			age: [],
			age_data: [],
			gender: [],
			gender_data: [],
			c_lng: "",
			c_lat: "",
			loaded: false,
			user: {
				email: "",
				name: "",
				dwellArea: "",
				favoriteArea: "",
				favoriteAreaCode: String,
			},
		};
	},
	created() {
		console.log("사용자 정보 얻기");

		getUserInfo(
			({ data }) => {
				console.log("메뉴의 사용자 정보 설정");
				console.log(data);
				//사용자 정보 설정
				this.user.email = data.email;
				this.user.name = data.name;
				this.user.dwellArea = data.dwellAreaName;
				this.user.favoriteArea = data.favoriteAreaName;
				if (this.user.dwellArea == null || this.user.dwellArea == "") {
					this.user.dwellArea = "설정하지 않음";
				}
				if (this.user.favoriteArea == null || this.user.favoriteArea == "") {
					this.user.favoriteArea = "설정하지 않음";
				} else {
					this.favoriteAreaCode = data.favoriteArea;
				}
			},
			(e) => {
				console.log("axios error");
				console.log(e);
			}
		);
	},
	mounted() {
		this.getData();
	},
	methods: {
		changeDongCode(newDongCode) {
			console.log("newDongCode: ", newDongCode);
			this.dong_code = newDongCode;
		},
		getData() {
			average(
				`${this.dong_code}`,
				(response) => {
					this.c_lng = response.data.geoList[0].c_lng;
					this.c_lat = response.data.geoList[0].c_lat;
					// geo 좌표 배열로 생성
					// console.log(response.data.geoList[0].geo.split("[").join().split("]").join().replace(/,, ,/g, "]|[").replace(",,,,", "[").replace(",,,,", "]").split("|"));
					this.map_data = response.data.geoList[0].geo
						.split("[")
						.join()
						.split("]")
						.join()
						.replace(/,, ,/g, "]|[")
						.replace(",,,,", "[")
						.replace(",,,,", "]")
						.split("|");
					console.log(this.map_data);

					// line Chart
					const line = response.data.averageList;
					this.dong = response.data.geoList[0].name;
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
					// pie Chart
					const ageList = response.data.ageList;
					len = ageList.length;
					for (i = 0; i < len; i++) {
						this.age.push(ageList[i].ageGroup);
						this.age_data.push(ageList[i].count);
					}

					const genderList = response.data.genderList;
					len = genderList.length;
					for (i = 0; i < len; i++) {
						this.gender.push(genderList[i].gender);
						this.gender_data.push(genderList[i].count);
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
#select-area {
	width: 500px;
	height: 50px;
	background: rgba(249, 247, 247, 0.7);
	box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 5px;
}

#average {
	width: 500px;
	height: 350px;
	background: rgba(249, 247, 247, 0.7);
	box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 5px;
}

#amount {
	width: 500px;
	height: 350px;
	margin-top: 25px;
	background: rgba(247, 247, 247, 0.7);
	box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 5px;
}

#pie {
	width: 500px;
	height: 350px;
	margin-top: 25px;
	background: rgba(247, 247, 247, 0.7);
	box-shadow: 3px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 5px;
}

#map {
	width: 100%;
	height: 100%;
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
	height: 100%;
	width: 100%;
	position: relative;
	animation: fadeInUp 2s;
}
</style>
