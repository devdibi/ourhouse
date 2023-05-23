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
			markerslocations: [],
			clickMarker: null,
		};
	},
	props: {
		// lat lng
		location: Object,
		locations: Array,
	},
	watch: {
		location: {
			// add parameter val
			handler(val) {
				console.log("=== watch handler ===");
				console.log(val.lat);
				console.log(val.lng);
				this.markMarker();
			},
			deep: true,
		},
		locations(loc) {
			console.log("카카오맵");
			// console.log(loc);

			this.markerslocations = [];
			loc.forEach((element) => {
				this.markerslocations.push(new kakao.maps.LatLng(element.lat, element.lng));
			});
			console.log(this.markerslocations);

			this.markAllMarkers();
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
		markAllMarkers() {
			// 마커 다 지우기
			// this.marker.setMap(null);

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
			// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
			var bounds = new kakao.maps.LatLngBounds();

			// var i, marker;
			let i;
			for (i = 0; i < this.markerslocations.length; i++) {
				// 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
				this.marker = new kakao.maps.Marker({ position: this.markerslocations[i] });
				this.marker.setMap(this.map);

				// LatLngBounds 객체에 좌표를 추가합니다
				bounds.extend(this.markerslocations[i]);
			}

			this.map.setBounds(bounds);
		},
		markMarker() {
			if (this.clickMarker != null) {
				console.log("==============");
				console.dir(this.clickMarker);
				this.clickMarker.setMap(null);
			}

			let offset = Number(this.location.lng) - 0.002;
			var moveLatLon = new kakao.maps.LatLng(this.location.lat, offset);

			// 지도 중심을 이동 시킵니다
			this.map.setCenter(moveLatLon);
			this.map.setLevel(2);

			var imageSrc = require("@/assets/icon/marker.png"), // 마커이미지의 주소입니다
				imageSize = new kakao.maps.Size(70, 70), // 마커이미지의 크기입니다
				imageOption = { offset: new kakao.maps.Point(35, 70) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
				markerPosition = new kakao.maps.LatLng(this.location.lat, this.location.lng); // 마커가 표시될 위치입니다

			// 마커를 생성합니다
			this.clickMarker = new kakao.maps.Marker({
				position: markerPosition,
				image: markerImage, // 마커이미지 설정
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			this.clickMarker.setMap(this.map);

			// // 마커 다 지우기
			// this.marker.setMap(null);
			// // 카카오맵 가져오기
			// var container = document.getElementById("map");
			// // 지도 offset
			// let offset = Number(this.location.lng) - 0.002;
			// // console.log("offset: ", offset);
			// // 카카오맵 설정
			// var options = {
			// 	center: new kakao.maps.LatLng(this.location.lat, offset),
			// 	level: 2,
			// };
			// // 카카오맵 생성
			// this.map = new kakao.maps.Map(container, options);
			// // Marker 객체 생성
			// this.marker = new kakao.maps.Marker({
			// 	position: new kakao.maps.LatLng(this.location.lat, this.location.lng),
			// });
			// // 마커 카카오맵에 추가하기
			// this.marker.setMap(this.map);
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
	/* background: green; */
	width: 100%;
	height: 100%;
	position: absolute;
}
</style>
