<template>
  <div>
    <Bar :options="Options" :data="chartData" />
    <button @click="loadYear()">연도별</button>
    <button @click="loadMonth()">월별</button>
  </div>
</template>
<script>
import { Bar } from "vue-chartjs";
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  data() {
    return {
      dong: "",
      chartData: {},
      Options: {},
    };
  },
  created() {
    this.dong = "hello";
    this.chartData = {
      labels: ["2015", "2016", "2017", "2018", "2019", "2020", "2021"],
      // x축의 데이터만 동일하면 상관없음
      datasets: [{ label: this.dong + "의 연도별 총 거개량", data: [19, 25, 20, 23, 26, 25, 30] }],
    };
    this.Options = {
      responsive: true,
      backgroundColor: [
        //색상
        "rgba(255, 99, 132, 0.2)",
        "rgba(54, 162, 235, 0.2)",
        "rgba(255, 206, 86, 0.2)",
        "rgba(75, 192, 192, 0.2)",
        "rgba(153, 102, 255, 0.2)",
        "rgba(255, 159, 64, 0.2)",
      ],
      borderWidth: 1, //경계선 굵기
    };
  },
  methods: {
    loadYear() {
      this.chartData = {
        labels: ["2015", "2016", "2017", "2018", "2019", "2020", "2021"],
        datasets: [{ label: `${this.dong}의 연도별 총 거개량`, data: [19, 25, 20, 23, 26, 25, 30] }],
      };
    },
    loadMonth() {
      this.chartData = {
        labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
        datasets: [{ label: `${this.dong}의 월별 총 거개량`, data: [19, 25, 20, 23, 26, 25, 19, 25, 20, 23, 26, 25] }],
      };
    },
  },
};
//
</script>

<style scoped></style>
