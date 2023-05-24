<template>
	<div>
		<div id="map"></div>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "KakaoMap",
	components: {},
	data() {
		return {
			map: Object,
			marker: Object,
			markers: [],
			comMarkers: [],
			tmp: 1,
			markerslocations: [],
			clickMarker: null,
			circle: null,
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
			handler() {
				console.log("=== watch handler ===");

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

			var imageSrc = require("@/assets/icon/marker.png"), // 마커이미지의 주소입니다
				imageSize = new kakao.maps.Size(70, 70), // 마커이미지의 크기입니다
				imageOption = { offset: new kakao.maps.Point(35, 70) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

			// var i, marker;
			let i;
			for (i = 0; i < this.markerslocations.length; i++) {
				// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
				var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
					markerPosition = this.markerslocations[i]; // 마커가 표시될 위치입니다

				// 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
				this.marker = new kakao.maps.Marker({
					position: markerPosition,
					image: markerImage, // 마커이미지 설정
				});
				this.marker.setMap(this.map);

				// LatLngBounds 객체에 좌표를 추가합니다
				bounds.extend(this.markerslocations[i]);
			}

			this.map.setBounds(bounds);
		},
		async getBusStation() {
			let result;
			// let lat = Math.ceil(this.location.lat * 100) / 100;
			// let lng = Math.ceil(this.location.lng * 100) / 100;

			let lat = this.location.lat;
			let lng = this.location.lng;

			let config = {
				method: "post", // 기본값
				baseURL: "http://localhost:9999/house/bus",
				data: {
					lat,
					lng,
				},
			};
			await axios(config)
				.then(({ data }) => {
					console.log("totalCount");
					console.log(data.response.body.totalCount);
					if (data.response.body.totalCount != 0) {
						result = data.response.body.items.item;
					} else {
						result = -1;
					}
					// this.sidoList = data.sidoList;
					// data.sidoList.forEach((element) => {
					// 	tmp.push({ text: element.name, value: element.sidoCode });
					// });
					// console.log(tmp);
					// tmp.unshift({ text: "시도 검색", value: null, disabled: true });
					// this.sidoList = tmp;
				})
				.catch((err) => {
					console.log(err);
				});
			return result;
		},
		async getCommercial() {
			let result;

			let lat = this.location.lat;
			let lng = this.location.lng;

			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/commercial",
				params: {
					lat,
					lng,
				},
			};
			await axios(config)
				.then(({ data }) => {
					console.log(data);
					result = data;
				})
				.catch((err) => {
					console.log(err);
				});
			return result;
		},
		async markMarker() {
			console.log("=== 버스 정보 읽기 ===");
			let bus = await this.getBusStation();
			console.log("=== 버스 정보 읽기 완료 ===");
			console.log("=== 상권 정보 읽기 ===");
			let commercial = await this.getCommercial();
			console.log("=== 상권 정보 읽기 완료 ===");

			console.log("=== 마커 초기화 ... ===");
			var bounds = new kakao.maps.LatLngBounds();
			let b;
			for (b = 0; b < this.markers.length; b++) {
				this.markers[b].setMap(null);
			}
			for (b = 0; b < this.comMarkers.length; b++) {
				this.comMarkers[b].setMap(null);
			}
			console.log("=== 마커 초기화 완료 ===");

			this.comMarkers = [];
			this.markers = [];

			if (bus != -1) {
				let imageSrc = require("@/assets/icon/bus.png"), // 마커이미지의 주소입니다
					imageSize = new kakao.maps.Size(35, 35), // 마커이미지의 크기입니다
					imageOption = { offset: new kakao.maps.Point(35, 70) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

				let i;
				for (i = 0; i < bus.length; i++) {
					// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
					let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
						markerPosition = new kakao.maps.LatLng(
							bus[i].gpslati - 0.00015,
							bus[i].gpslong + 0.0001
						); // 마커가 표시될 위치입니다

					// 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
					this.marker = new kakao.maps.Marker({
						position: markerPosition,
						image: markerImage, // 마커이미지 설정
					});

					this.markers.push(this.marker);
					this.markers[i].setMap(this.map);

					// LatLngBounds 객체에 좌표를 추가합니다
					bounds.extend(new kakao.maps.LatLng(bus[i].gpslati, bus[i].gpslong));
				}

				this.map.setBounds(bounds);
			}

			if (commercial.length != 0) {
				let imageSrc = require("@/assets/icon/commercial.png"), // 마커이미지의 주소입니다
					imageSize = new kakao.maps.Size(35, 35), // 마커이미지의 크기입니다
					imageOption = { offset: new kakao.maps.Point(35, 70) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

				let j;
				for (j = 0; j < commercial.length; j++) {
					// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
					let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
						markerPosition = new kakao.maps.LatLng(commercial[j].lat, commercial[j].lng); // 마커가 표시될 위치입니다
					// 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
					this.marker = new kakao.maps.Marker({
						position: markerPosition,
						image: markerImage, // 마커이미지 설정
					});

					this.comMarkers.push(this.marker);
					this.comMarkers[j].setMap(this.map);

					// LatLngBounds 객체에 좌표를 추가합니다
					bounds.extend(new kakao.maps.LatLng(commercial[j].lat, commercial[j].lng));
				}

				this.map.setBounds(bounds);
			}

			// 아파트 좌표
			if (this.clickMarker != null) {
				// console.log("==============");
				// console.dir(this.clickMarker);
				this.clickMarker.setMap(null);
			}

			let offset = Number(this.location.lng) - 0.002;
			var moveLatLon = new kakao.maps.LatLng(this.location.lat, offset);

			// 지도 중심을 이동 시킵니다
			this.map.setCenter(moveLatLon);
			this.map.setLevel(3);

			let imageSrc = require("@/assets/icon/smarker.png"), // 마커이미지의 주소입니다
				imageSize = new kakao.maps.Size(70, 70), // 마커이미지의 크기입니다
				imageOption = { offset: new kakao.maps.Point(35, 70) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

			// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
			let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
				markerPosition = new kakao.maps.LatLng(this.location.lat, this.location.lng); // 마커가 표시될 위치입니다

			// 마커를 생성합니다
			this.clickMarker = new kakao.maps.Marker({
				position: markerPosition,
				image: markerImage, // 마커이미지 설정
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			this.clickMarker.setMap(this.map);

			kakao.maps.event.addListener(this.clickMarker, "click", () => {
				console.log(this.$router.push("/"));
			});

			// ** 원 표시 **
			var centerPosition = new kakao.maps.LatLng(this.location.lat, this.location.lng); // 원의 중심좌표 입니다

			if (this.circle != null) {
				this.circle.setMap(null);
			}
			// 원 객체를 생성합니다
			this.circle = new kakao.maps.Circle({
				center: centerPosition, // 원의 중심좌표입니다
				radius: 500, // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
				strokeWeight: 1, // 선의 두께입니다
				strokeColor: "#00a0e9", // 선의 색깔입니다
				strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
				strokeStyle: "solid", // 선의 스타일입니다
				fillColor: "#00a0e9", // 채우기 색깔입니다
				fillOpacity: 0.2, // 채우기 불투명도입니다
			});

			// 원을 지도에 표시합니다
			this.circle.setMap(this.map);
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
