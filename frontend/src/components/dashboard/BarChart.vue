<template>
  <div>
    <Bar :options="Options" :data="chartData" />
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
      chartData: {
        labels: ["2016", "2017", "2018", "2019", "2020", "2021"],
        // x축의 데이터만 동일하면 상관없음
        datasets: [{ data: [19, 25, 20, 23, 26, 25] }],
      },
      Options: {
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
      },
    };
  },
  created() {},
  methods: {},
};
//
</script>

<style scoped></style>
