<template>
  <div style="padding: 20px">
    <Bar :options="Options" :data="chartData" />
    <div style="margin-top: 30px; margin-right: 20px; float: right; color: #939393">
      <button @click="loadYear()">연도별</button>
      &nbsp;&nbsp;|&nbsp;&nbsp;
      <button @click="loadMonth()">월별</button>
    </div>
  </div>
</template>
<script>
import { Bar } from "vue-chartjs";
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: "BarChart",
  components: { Bar },
  props: {
    year: {
      type: Array,
    },
    year_data: {
      type: Array,
    },
    month: {
      type: Array,
    },
    month_data: {
      type: Array,
    },
    dong: {
      type: String,
    },
  },
  data() {
    return {
      color: [],
      chartData: {
        labels: [],
        datasets: [],
      },
      Options: {
        responsive: true,
        borderWidth: 1,
      },
    };
  },
  created() {
    this.loadYear();
  },
  methods: {
    loadYear() {
      this.chartData = {
        labels: this.year,
        datasets: [
          {
            label: this.dong + "의 연도별 연도별 총 거래량(2015 ~ 2022.04)",
            data: this.year_data,
            backgroundColor: [
              // 색상
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(75, 192, 192, 0.2)",
            ],
          },
        ],
      };
    },
    loadMonth() {
      this.chartData = {
        labels: this.month,
        datasets: [
          {
            label: this.dong + "의 연도별 월별 총 거래량(2015 ~ 2022.04)",
            data: this.month_data,
            backgroundColor: [
              // 색상
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 99, 132, 0.2)",
              "rgba(75, 192, 192, 0.2)",
            ],
          },
        ],
      };
    },
    load() {},
  },
};
//
</script>

<style scoped></style>
