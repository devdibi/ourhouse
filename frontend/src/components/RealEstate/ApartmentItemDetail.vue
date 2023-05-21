<template>
  <div id="detail">
    <div class="tc">
      <h2 class="bold">{{ showDetailWindowData.name }}</h2>
    </div>
    <!-- 건물 정보 -->
    <div class="apart-info">
      <div>
        <h4>건물 정보</h4>
      </div>
      <div class="info-container">
        <li class="add-top-border">
          <div class="info-title">통계</div>
          <div class="info-data">
            <p class="info-first-data bold">평균 면적</p>
            <p class="info-second-data">{{ showDetailWindowData.areaAvg }}㎡</p>
            <div class="info-data-second">
              <p class="info-first-data bold">평균 거래 가격</p>
              <p class="info-second-data">{{ price }} 만원</p>
            </div>
          </div>
        </li>
        <li class="add-top-border">
          <div class="info-title">주소</div>
          <div class="info-data">
            <p class="info-first-data bold">지번 주소</p>
            <p class="info-second-data">{{ showDetailWindowData.jibunAddress }}</p>
            <div class="info-data-second">
              <p class="info-first-data bold">도로명 주소</p>
              <p class="info-second-data">{{ showDetailWindowData.roadAddress }}</p>
            </div>
          </div>
        </li>
        <li class="add-top-border">
          <div class="info-title">건축년도</div>
          <div class="info-data">{{ showDetailWindowData.buildYear }}년</div>
        </li>
      </div>
    </div>
    <!-- 건물 정보 -->

    <!-- 거래 정보 -->
    <div class="apart-info" style="height: calc(100% - 400px)">
      <div>
        <h3>거래 정보</h3>
      </div>
      <div id="sort-option">
        <a href="#">최신 순</a>
        <span> | </span>
        <a href="#">오래된 순</a>
        <span> | </span>
        <a href="#">가격 순</a>
        <span> | </span>
        <a href="#">면적 순</a>
      </div>
      <div id="hr"></div>
      <div style="height: calc(100% - 80px); overflow-y: auto">
        <!-- <div> -->
        <div>
          <ApartmentItemTrade v-for="(deal, index) in showDetailWindowData.deals" :key="index" :deal="deal" />
        </div>
      </div>
    </div>
    <!-- 건물 정보 -->
    <!-- <div id="close-detail">X</div> -->
  </div>
</template>

<script>
import ApartmentItemTrade from "./ApartmentItemTrade.vue";

export default {
  data() {
    return {};
  },
  props: ["showDetailWindowData"],
  components: {
    ApartmentItemTrade,
  },
  computed: {
    price() {
      if (this.showDetailWindowData.priceAvg == undefined) return;

      return this.showDetailWindowData.priceAvg.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.info-first-data {
  margin: 0px;
}
.info-second-data {
  margin: 0px;
  margin-top: 4px;
}
.info-data-second {
  border-top: 1.5px solid lightgrey;
  margin-top: 16px;
  padding-top: 16px;
}
#sort-option {
  /* border: 1px black solid; */
  font-size: 15px;
  text-align: end;
  color: grey;
}
dl {
  /* border: 1px black solid; */
  margin: 1px;
}
h2 {
  margin-top: 20px;
}
h3 {
  margin: 0px;
  font-size: 24px;
  line-height: 38px;
  letter-spacing: -0.5px;
  margin-top: 20px;
}
h4 {
  margin: 0px;
  font-size: 24px;
  line-height: 38px;
  letter-spacing: -0.5px;
  margin-bottom: 20px;
  margin-top: 20px;
}
a {
  color: grey;
  cursor: pointer;
  text-decoration: none;
}
.info-title {
  width: 160px;
  font-size: 16px;
  line-height: 26px;
  font-weight: 700;
  text-align: end;
  padding-right: 30px;
}
.info-data {
  -webkit-box-flex: 1;
  flex-grow: 1;
  font-size: 16px;
  line-height: 26px;
  color: rgb(34, 34, 34);
}
.add-top-border {
  border-top: 1.5px solid lightslategrey;
}
.tc {
  text-align: center;
}
.bold {
  font-weight: bold;
}
#detail {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1;
  left: 406px;
  width: 560px;
  background: white;
  overflow-y: auto;
}
#close-detail {
  position: absolute;
  /* left: 966px; */
  right: -42px;
  top: 20px;
  width: 42px;
  height: 42px;
  border: 1px solid rgba(0, 0, 0, 0.16);
  border-left: 0;
  border-top-right-radius: 1px;
  border-bottom-right-radius: 1px;
  background-color: red;
  z-index: 2500;
}
#hr {
  margin-top: 10px;
  border-bottom: 2px grey solid;
  /* width: 90%; */
  margin-left: auto;
  margin-right: auto;
}
.apart-info {
  /* border: 1px black solid; */
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 15px;
}

.apart-info li {
  display: flex;
  padding: 16px 0px;
  list-style: none;
}
</style>
