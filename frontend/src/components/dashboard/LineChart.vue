<template>
  <div style="padding: 20px">
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
  props: {
    year: {
      type: Array,
    },
    average: {
      type: Array,
    },
    dong: {
      type: String,
    },
  },
  data() {
    return {
      chartData: {
        labels: this.year,
        datasets: [
          {
            label: this.dong + "의 연도별 평균 거래가격 추세",
            data: this.average,
            backgroundColor: "rgba(148, 76, 249, 0.7)",
            borderColor: "#d9d9d9",
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
