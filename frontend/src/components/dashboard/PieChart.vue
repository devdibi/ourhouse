<template>
  <div style="padding: 20px">
    <Pie :opdtions="chartOptions" :data="chartData" style="max-height: 250px" />
    <div style="margin-top: 30px; margin-right: 20px; float: right; color: #939393; margin-bottom: 10px">
      <button @click="loadAge()">연령대</button>
      &nbsp;&nbsp;|&nbsp;&nbsp;
      <button @click="loadGender()">성별</button>
    </div>
  </div>
</template>
<script>
import { Pie } from "vue-chartjs";

import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale);
export default {
  name: "LineChart",
  components: { Pie },
  props: {
    gender: {
      type: Array,
    },
    gender_data: {
      type: Array,
    },
    age: {
      type: Array,
    },
    age_data: {
      type: Array,
    },
  },
  data() {
    return {
      chartData: {
        labels: this.gender,
        datasets: [
          {
            label: "성별에 따른 관심도(관심지역)",
            backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
            data: this.gender_data,
          },
        ],
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  created() {},
  methods: {
    loadAge() {
      this.chartData = {
        labels: this.age,
        datasets: [
          {
            label: "연령별(10 ~ 80대) 관심도(관심지역)",
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
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
            data: this.age_data,
          },
        ],
      };
      this.chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
      };
    },
    loadGender() {
      this.chartData = {
        labels: this.gender,
        datasets: [
          {
            label: "성별에 따른 관심도(관심지역)",
            backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
            data: this.gender_data,
          },
        ],
      };
      this.chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
      };
    },
  },
};
</script>

<style scoped></style>
