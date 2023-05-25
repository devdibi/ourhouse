<template>
	<div class="p-1 deal-container" v-if="deal.showWindow">
		<div class="border-top border-2 w-75"></div>
		<div class="d-flex flex-row pink text-center">
			<div class="p-2 w-25">거래 금액</div>
			<div class="p-2 w-50">{{ price }} 만원</div>
		</div>
		<div class="border-top border-2 w-75"></div>
		<div class="d-flex flex-row text-center">
			<div class="p-2 w-25">거래 날짜</div>
			<div class="p-2 w-50">{{ deal.year }}년 {{ deal.month }}월</div>
		</div>
		<div class="d-flex text-center border-top border-2">
			<div class="p-2 w-25 flex-fill">면적</div>
			<div class="p-2 w-25 flex-fill">{{ deal.area }}㎡</div>
			<div class="p-2 w-25 flex-fill">층</div>
			<div class="p-2 w-25 flex-fill">{{ deal.floor }}층</div>
		</div>
		<!-- dealLike -->
		<div class="p-1 square">
			<img
				src="@/assets/icon/filled-star.svg"
				v-if="starFlag"
				class="w-100"
				@click="dislikeDealInfo"
			/>
			<img src="@/assets/icon/empty-star.svg" v-else class="w-100" @click="likeDealInfo" />
		</div>
	</div>
</template>

<script>
import { API } from "@/api/axios-jwt";
import { mapActions, mapGetters } from "vuex";

export default {
	data() {
		return {
			starFlag: this.deal.dealLike,
		};
	},
	watch: {
		deal(val) {
			// console.log(val.dealLike);
			this.starFlag = val.dealLike;
		},
	},
	computed: {
		price() {
			return this.deal.price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		},
		...mapGetters({
			token: "getAccessToken",
		}),
		flag() {
			return this.check();
		},
	},
	methods: {
		...mapActions(["setNextURL"]),
		likeDealInfo() {
			console.log("=== likeDealInfo ===");

			if (this.token === -1) {
				console.log("=== Access Token이 없음 ===");
				this.setNextURL("/land");
				console.log(this.$store.state.prevURL);

				alert("로그인 하셈");
				this.$router.push("/user/login");

				return;
			}

			let http = API();
			let dealCode = this.deal.dealCode;

			http
				.post("/house/like_deal", JSON.stringify(dealCode))
				.then((res) => {
					this.starFlag = true;
					console.log(res);
				})
				.catch((err) => {
					console.log(err);
				});
		},
		dislikeDealInfo() {
			console.log("=== dislikeDealInfo ===");

			let http = API();
			let dealCode = this.deal.dealCode;

			http
				.delete("/house/dislike_house", {
					data: {
						dealCode: dealCode,
					},
				})
				.then((res) => {
					this.starFlag = false;
					console.log(res);
				})
				.catch((err) => {
					console.log(err);
				});
		},
	},
	props: {
		deal: Object,
	},
};
</script>

<style scoped>
.pink {
	color: #f08ebf;
	font-weight: bold;
}

.square {
	width: 70px;
	height: 70px;
	position: absolute;
	top: 10px;
	right: 25px;
}

.deal-container {
	position: relative;
	background-clip: content-box;
	background-color: rgba(255, 193, 204, 0.2);
}
</style>
