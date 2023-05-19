<template>
	<div id="container">
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
};
</script>

<style scoped>
#deal-info-head {
	display: inline;
}
#container {
	margin-top: 10px;
}
.info-title {
	display: inline-block;
	text-align: end;
	width: 15%;
	/* border: 1px black solid; */
}
.info-data {
	display: inline-block;
}
h3 {
	margin: 0px;
}
dl {
	margin: 0px;
}
</style>
