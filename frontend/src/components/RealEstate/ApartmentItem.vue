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
				@click="likeHouseInfo"
			/>
			<img
				id="like-img"
				v-if="isLike"
				class=""
				src="@/assets/icon/filled-star.svg"
				@click="dislikeHouseInfo"
			/>
		</div>
		<div class="" id="apartment-name">{{ apartDealData.name }}</div>
		<div class="w-15" style="padding: 10px">
			<!-- <svg-icon type="mdi" :path="path"></svg-icon> -->
			<img src="@/assets/icon/cancel.svg" v-if="flag" />
			<img src="@/assets/icon/arrow.svg" v-else />
		</div>
	</div>
</template>

<script>
import { API } from "@/api/axios-jwt";
// import axios from "axios";
import { mapGetters } from "vuex";

export default {
	name: "ApartmentItem",
	components: {},
	computed: {
		...mapGetters({
			token: "getAccessToken",
		}),
		flag() {
			return this.check();
		},
	},
	data() {
		return {
			// apartmentName: "반포자이",
			isLike: this.apartDealData.houseLike,
			isHover: false,
			isClick: false,
			lastClick: Number,
		};
	},
	props: {
		apartDealData: Object,
		index: Number,
		lastClickIndex: Number,
		show: Boolean,
	},
	methods: {
		likeHouseInfo() {
			// let http = API();
			// let apartmentCode = this.apartDealData.aptCode;
			// let jwt = {
			// 	Authorization:
			// 		"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBzc2FmeS5jb20iLCJpYXQiOjE2ODQ3MzAyOTQsImV4cCI6MTY4NDczMzg5NH0.ht8KBJ4R6zSkWVkkIHKovLQrfMwEaX7FdznmymvhWZk",
			// };
			// http
			// 	.post("/house/like_house", JSON.stringify(apartmentCode), { headers: jwt })
			// 	.then((res) => {
			// 		console.log(res);
			// 	})
			// 	.catch((err) => {
			// 		console.log(err);
			// 	});

			console.log(this.token);

			let http = API();
			let apartmentCode = this.apartDealData.aptCode;

			http
				.post("/house/like_house", JSON.stringify(apartmentCode))
				.then((res) => {
					console.log(res);
				})
				.catch((err) => {
					console.log(err);
				});
		},
		dislikeHouseInfo() {
			console.log(this.token);

			let http = API();
			let apartmentCode = this.apartDealData.aptCode;

			http
				.delete("/house/dislike_house", {
					data: {
						apartmentCode: apartmentCode,
					},
				})
				.then((res) => {
					console.log(res);
				})
				.catch((err) => {
					console.log(err);
				});

		},
		clickLikeBtn() {
			// console.log(this.isLike);
			this.isLike = !this.isLike;
		},
		toggleDetailWindow(flag) {
			this.isHover = flag;
		},
		showDetailWindow(e) {
			if (e.target.id === "like-img") {
				return;
			}
			// console.dir(e.target.id);
			let data = {
				id: this.index,
				lat: this.apartDealData.lat,
				lng: this.apartDealData.lng,
			};
			this.$emit("showDetailWindow", data);
		},
		check() {
			if (this.index === this.lastClickIndex && this.show) {
				return true;
			} else {
				return false;
			}
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
