<template>
  <div
    id="item-container"
    class="w-100"
    style="height: 60px"
    :class="{ 'bg-black': isHover }"
    @mouseover="toggleDetailWindow(true)"
    @mouseout="toggleDetailWindow(false)"
    @click="showDetailWindow"
  >
    <div class="w-15" style="padding: 10px" @click="clickLikeBtn">
      <img
        id="like-img"
        v-if="!isLike"
        class=""
        src="@/assets/icon/empty-star.svg"
        alt=""
      />
      <img
        id="like-img"
        v-if="isLike"
        class=""
        src="@/assets/icon/filled-star.svg"
        alt=""
      />
    </div>
    <div class="" id="apartment-name">
      {{ apartDealData.name }}
    </div>
    <div class="w-15" style="padding: 10px">
      <!-- <svg-icon type="mdi" :path="path"></svg-icon> -->
      <img src="@/assets/icon/arrow.svg" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  name: "ApartmentItem",
  components: {},
  data() {
    return {
      // apartmentName: "반포자이",
      isLike: this.apartDealData.houseLike,
      isHover: false,
    };
  },
  props: ["apartDealData", "index"],
  methods: {
    clickLikeBtn() {
      console.log(this.isLike);
      this.isLike = !this.isLike;
    },
    toggleDetailWindow(flag) {
      this.isHover = flag;
    },
    showDetailWindow(e) {
      if (e.target.id === "like-img") {
        return;
      }
      console.dir(e.target.id);
      let data = {
        id: this.index,
        lat: this.apartDealData.lat,
        lng: this.apartDealData.lng,
      };
      this.$emit("showDetailWindow", data);
    },
  },
};
</script>

<style scoped>
.bg-black {
  background: #f2f8fd;
}
#item-container {
  /* border: 1px black solid; */
  /* 위 | 오른쪽 | 아래 | 왼쪽 */
  border-style: none none solid none;
  border-bottom-width: 2.5px;

  /* margin-left: auto;
	margin-right: auto; */
  /* margin-bottom: 3px; */
  width: 100%;
}
div {
  display: flex;
  /* text-align: center; */
  justify-content: center;
  /* display: inline-block; */
  /* border: 1px black solid; */
  height: 100%;
}
#apartment-name {
  font-size: 25px;
  font-weight: bold;
  width: 70%;
  align-items: center;
}

.w-15 {
  width: 15%;
}

img {
  display: inline;
}
</style>
