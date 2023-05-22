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
      chartData: {
        labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [40, 0, 10, 40, 39, 80, 100],
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
