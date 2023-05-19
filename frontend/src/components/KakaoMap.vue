<template>
	<div>
		<div id="map"></div>
	</div>
</template>

<script>
export default {
	name: "KakaoMap",
	components: {},
	data() {
		return {
			map: Object,
			marker: Object,
			tmp: 1,
		};
	},
	props: {
		// lat lng
		location: Object,
	},
	watch: {
		location: {
			// add parameter val
			handler() {
				// console.log("=== watch handler ===");
				// console.log(val.lat);
				// console.log(val.lng);
				this.markMarker();
			},
			deep: true,
		},
	},
	created() {},
	mounted() {
		if (window.kakao && window.kakao.maps) {
			this.initMap();
		} else {
			this.addScript();
		}
	},
	unmounted() {},
	methods: {
		markMarker() {
			// 마커 다 지우기
			this.marker.setMap(null);

			// 카카오맵 가져오기
			var container = document.getElementById("map");

			// 지도 offset
			let offset = Number(this.location.lng) - 0.002;
			// console.log("offset: ", offset);

			// 카카오맵 설정
			var options = {
				center: new kakao.maps.LatLng(this.location.lat, offset),
				level: 2,
			};

			// 카카오맵 생성
			this.map = new kakao.maps.Map(container, options);

			// Marker 객체 생성
			this.marker = new kakao.maps.Marker({
				position: new kakao.maps.LatLng(this.location.lat, this.location.lng),
			});

			// 마커 카카오맵에 추가하기
			this.marker.setMap(this.map);
		},
		initMap() {
			var container = document.getElementById("map");
			var options = {
				center: new kakao.maps.LatLng(33.450701, 126.570667),
				level: 3,
			};
			this.map = new kakao.maps.Map(container, options);
			//마커추가하려면 객체를 아래와 같이 하나 만든다.
			this.marker = new kakao.maps.Marker({
				position: this.map.getCenter(),
			});
			// this.marker.setMap(null);
			this.marker.setMap(this.map);
		},
		addScript() {
			console.log("addScript");
			const script = document.createElement("script");
			/* global kakao */
			script.onload = () => kakao.maps.load(this.initMap);
			script.src =
				"http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=bea75f87561964b54f6fc1af28897e61";
			document.head.appendChild(script);
		},
	},
};
</script>

<style scoped>
#map {
	background: green;
	width: 100%;
	height: 100%;
	position: absolute;
}
</style>
