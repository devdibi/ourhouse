<template>
  <div>
    <div>
      <div id="aptContainer">
        <h2 id="title">관심 단지 목록</h2>
				<b-table :items="likeApts" id="table"></b-table>
			</div>
    </div>
  </div>
</template>

<script>
import {getFavoriteHouses } from "@/api/user"
export default {
  name: 'LikeApts',
  components: {},
  data() {
    return {
      likeApts:[],
    };
  },
  created() {
    getFavoriteHouses(
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
							"평균 가격":data.list[i].priceAvg + " 만원",
							"평균 면적": data.list[i].areaAvg + '㎡',
						}
						this.likeApts.push(tmpApt);
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