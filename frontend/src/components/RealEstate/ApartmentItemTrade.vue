<template>
	<div class="p-1 deal-container">
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
		<div class="p-1 square">
			<img src="@/assets/icon/empty-star.svg" alt="" class="w-100" />
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
	props: {
		deal: Object,
	},
	computed: {
		price() {
			return this.deal.price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		},
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
