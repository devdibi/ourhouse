<template>
  <div>
    <LineChartGenerator :options="options" :data="chartData" style="height: 300px" />
  </div>
</template>
<script>
import { Line as LineChartGenerator } from "vue-chartjs";

import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement } from "chart.js";

ChartJS.register(Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement);
export default {
  name: "LineChart",
  components: { LineChartGenerator },
  data() {
    return {
      region: "",
      value: [40, 0, 10, 40, 39, 80, 100, 70],
      chartData: {
        labels: ["2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"],
        datasets: [],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  created() {
    this.region = "화북동";
    this.chartData.datasets.push({
      label: this.region + "의 연도별 평균 거래가격 추세",
      backgroundColor: "#f87979",
      data: this.value,
    });
  },
  methods: {},
};
</script>

<style scoped></style>
