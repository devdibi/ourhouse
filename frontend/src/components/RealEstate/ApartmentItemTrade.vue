<template>
	<div class="p-1 deal-container">
		<div class="border-top border-2 w-75"></div>
		<div class="d-flex flex-row pink text-center">
			<div class="p-2 w-25">거래 금액</div>
			<div class="p-2 w-50">{{ deal.price }}</div>
		</div>
		<div class="border-top border-2 w-75"></div>
		<div class="d-flex flex-row text-center">
			<div class="p-2 w-25">거래 날짜</div>
			<div class="p-2 w-50">{{ deal.year }}.{{ deal.month }}</div>
		</div>
		<div class="d-flex text-center border-top border-2">
			<div class="p-2 w-25 flex-fill">면적</div>
			<div class="p-2 w-25 flex-fill">{{ deal.area }}</div>
			<div class="p-2 w-25 flex-fill">층</div>
			<div class="p-2 w-25 flex-fill">{{ deal.floor }}</div>
		</div>
		<div class="p-1 square">
			<img src="@/assets/icon/empty-star.svg" alt="" class="w-100" />
		</div>
	</div>
	<!-- <div id="container">
		<div class="deal-info-head">
			<dt class="info-title">거래 날짜</dt>
			<dd class="info-data">2019년 1월</dd>
			<img src="@/assets/icon/empty-star.svg" alt="" />
		</div>
		<div>
			<dl>
				<dt class="info-title">층</dt>
				<dd class="info-data">{{ deal.floor }}</dd>
			</dl>
			<dl>
				<dt class="info-title">거래 금액</dt>
				<dd class="info-data">{{ deal.price }}</dd>
			</dl>
			<dl>
				<dt class="info-title">면적</dt>
				<dd class="info-data">{{ deal.area }}</dd>
			</dl>
		</div>
	</div> -->
</template>

<script>
export default {
	data() {
		return {};
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
