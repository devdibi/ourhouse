<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  name: "MapChart",
  components: {},
  props: {
    areas: {
      type: Array,
    },
    dong: {
      type: String,
    },
  },
  data() {
    return {
      lat: [],
      lng: [],
      polygon: [],
    };
  },
  created() {},

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.addMap();
    } else {
      this.addScript();
    }
  },
  methods: {
    addScript() {
      console.log("addScript");
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f66b2cab49a7421a828121c529ee0415";
      document.head.appendChild(script);
    },
    addMap() {
      // console.log(this.areas.join().replace(/\],\[/g, "|").replace("[", "").replace("]", "").split("|")[0].split(", "));
      for (var j = 0; j < this.areas.length; j++) {
        this.lng.push(this.areas.join().replace(/\],\[/g, "|").replace("[", "").replace("]", "").split("|")[j].split(", ")[0]);
        this.lat.push(this.areas.join().replace(/\],\[/g, "|").replace("[", "").replace("]", "").split("|")[j].split(", ")[1]);
        this.polygon.push(new kakao.maps.LatLng(this.lat[j], this.lng[j]));
      }
      var areas = [
        {
          name: this.dong,
          path: this.polygon,
        },
      ];

      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.lat[0], this.lng[0]), // 지도의 중심좌표
          level: 4, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption),
        customOverlay = new kakao.maps.CustomOverlay({}),
        infowindow = new kakao.maps.InfoWindow({ removable: true });

      // 지도에 영역데이터를 폴리곤으로 표시합니다
      for (var i = 0, len = areas.length; i < len; i++) {
        displayArea(areas[i]);
      }

      // 다각형을 생상하고 이벤트를 등록하는 함수입니다
      function displayArea(area) {
        // 다각형을 생성합니다
        var polygon = new kakao.maps.Polygon({
          map: map, // 다각형을 표시할 지도 객체
          path: area.path,
          strokeWeight: 2,
          strokeColor: "#004c80",
          strokeOpacity: 0.8,
          fillColor: "#fff",
          fillOpacity: 0.7,
        });

        // 다각형에 mouseover 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 변경합니다
        // 지역명을 표시하는 커스텀오버레이를 지도위에 표시합니다
        kakao.maps.event.addListener(polygon, "mouseover", function (mouseEvent) {
          polygon.setOptions({ fillColor: "#09f" });

          customOverlay.setContent('<div class="area">' + area.name + "</div>");

          customOverlay.setPosition(mouseEvent.latLng);
          customOverlay.setMap(map);
        });

        // 다각형에 mousemove 이벤트를 등록하고 이벤트가 발생하면 커스텀 오버레이의 위치를 변경합니다
        kakao.maps.event.addListener(polygon, "mousemove", function (mouseEvent) {
          customOverlay.setPosition(mouseEvent.latLng);
        });

        // 다각형에 mouseout 이벤트를 등록하고 이벤트가 발생하면 폴리곤의 채움색을 원래색으로 변경합니다
        // 커스텀 오버레이를 지도에서 제거합니다
        kakao.maps.event.addListener(polygon, "mouseout", function () {
          polygon.setOptions({ fillColor: "#fff" });
          customOverlay.setMap(null);
        });

        // 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다
        kakao.maps.event.addListener(polygon, "click", function (mouseEvent) {
          var content =
            '<div class="info">' + '   <div class="title">' + area.name + "</div>" + '   <div class="size">총 면적 : 약 ' + Math.floor(polygon.getArea()) + " m<sup>2</sup></div>" + "</div>";

          infowindow.setContent(content);
          infowindow.setPosition(mouseEvent.latLng);
          infowindow.setMap(map);
        });
      }
    },
  },
};
</script>

<style scoped>
#map {
  height: 100%;
}
.area {
  position: absolute;
  background: #fff;
  border: 1px solid #888;
  border-radius: 3px;
  font-size: 12px;
  top: -5px;
  left: 15px;
  padding: 2px;
}

.info {
  font-size: 12px;
  padding: 5px;
}
.info .title {
  font-weight: bold;
}
</style>
