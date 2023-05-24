<template>
  <div>
    <div>
      <div id="aptContainer">
        <h2 id="title">관심 거래 목록</h2>
				<b-table :items="likeDeals" id="table"></b-table>
			</div>
    </div>
  </div>
</template>

<script>
import {getFavoriteDeals } from "@/api/user"
export default {
  name: 'LikeDeals',
  components: {},
  data() {
    return {
      likeDeals:[],
    };
  },
  created() {
    getFavoriteDeals(
      ({ data }) => {
        console.log(data)
        if (data.message == "success") { 
          console.log(data.list.length);
          console.log(data.list);
          for (var i = 0; i < data.list.length; i++){
						var tmpApt = {
							"번호":i+1,
							"주소": data.list[i].sidoName + " " + data.list[i].gugunName + " " + data.list[i].jibunAddress,
              "아파트 이름": data.list[i].aptName,
              "층":data.list[i].floor + "층",
              "가격": data.list[i].price + " 만원",
							"면적": data.list[i].area + '㎡',
						}
						this.likeDeals.push(tmpApt);
					}
        }
      },
      (error) => {console.log(error)}
    );
  },
  methods: {},
};
</script>

<style scoped>
  #aptContainer{
    width: 100%;
    margin: auto;
    padding-top: 70px;
  }

  #table{
    max-width: 1000px;
    margin: auto;
    text-align: center;
  }

  #title{
    text-align: center;
    margin-bottom: 40px;
  }
</style>