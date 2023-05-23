<template>
  <div>
    <div>
      <button @click="loadAge()">연령대</button>
      <button @click="loadGender()">성별</button>
    </div>
    <Pie :options="chartOptions" :data="chartData" style="max-height: 300px" />
  </div>
</template>
<script>
import { Pie } from "vue-chartjs";

import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale);
export default {
  name: "LineChart",
  components: { Pie },
  data() {
    return {
      chartData: {
        labels: ["남", "여"],
        datasets: [
          {
            label: "성별에 따른 관심도(관심지역)",
            backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
            data: [40, 19],
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
        labels: ["10대", "20대", "30대", "40대", "50대", "60대", "70대", "80대"],
        datasets: [
          {
            label: "연령별(10 ~ 80대) 관심도(관심지역)",
            backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
            data: [40, 19],
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
        labels: ["남", "여"],
        datasets: [
          {
            label: "성별에 따른 관심도(관심지역)",
            backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
            data: [40, 19],
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
