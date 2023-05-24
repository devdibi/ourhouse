<template>
  <div>
    <div class="option-window-container">
      <a class="option-title" @click="$emit('toggleOptionWindow')">
        {{ optionName }}
        <span class="arrow" :class="{ clicked: isOpen }">
          <img src="@/assets/icon/optionarrow.svg" />
        </span>
      </a>
      <div class="option-window" v-show="isOpen">
        <div class="option">
          <h4>{{ optionName }}</h4>
        </div>
        <div class="slider-container">
          <div class="display">
            {{ displayText }}
          </div>
          <vue-slider
            v-model="floorValue"
            :enable-cross="false"
            :min="min"
            :max="max"
            @change="setDetailOption"
          ></vue-slider>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/* Slide-Bar */
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";

export default {
  name: "ApartmentItemOption",
  components: {
    VueSlider,
  },
  data() {
    return {
      // displayText: "전체",
      floorValue: [this.min, this.max],
      unit: "",
    };
  },
  computed: {
    displayText: function () {
      let text = "";
      if (this.min === this.floorValue[0] && this.max === this.floorValue[1]) {
        text = "전체";
      } else if (this.min === this.floorValue[0]) {
        text = `${this.floorValue[1]}${this.unit} 이하`;
      } else if (this.max === this.floorValue[1]) {
        text = `${this.floorValue[0]}${this.unit}  이상`;
      } else {
        text = `${this.floorValue[0]}${this.unit}  ~ ${this.floorValue[1]}${this.unit} `;
      }
      return text;
    },
  },
  methods: {
    setDetailOption() {
      console.log(`=== ${this.optionName} ===`);
      console.log(this.floorValue);

      let option = {
        optionName: this.optionName,
        value: this.floorValue,
      };

      this.$emit("changeDetailOption", option);
    },
  },
  props: {
    optionName: String,
    isOpen: Boolean,
    min: Number,
    max: Number,
  },
  created() {
    if (this.optionName === "가격대") {
      this.unit = "억원";
    } else if (this.optionName === "면적") {
      this.unit = "㎡";
    } else if (this.optionName === "층수") {
      this.unit = "층";
    }
  },
};
</script>

<style scoped>
.display {
  margin-bottom: 2.5rem;
  text-align: center;
  font-size: 20px;
}

.clicked {
  transform: rotate(180deg);
}

.option-window-container {
  /* display: inline-block; */
  position: relative;
  margin-left: 8px;
}

.option-window {
  width: 500px;
  height: 300px;
  position: absolute;
  top: 50px;
  left: 0;
  z-index: 100;
  border: 1px solid rgba(0, 0, 0, 0.2);
  padding: 25px 25px 10px;
  background-clip: padding-box;
  background-color: #fff;
  white-space: normal;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
}

.option-title {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border: 1px solid rgba(0, 0, 0, 0.12);
  font-size: 12px;
  line-height: 19px;
  letter-spacing: -0.43px;
  display: inline-block;
  position: relative;
  height: 32px;
  padding: 6px 29px 5px 9px;
  border-radius: 2px;
  background-color: #fff;
  vertical-align: top;
  color: #444;
  text-decoration: none;
  cursor: pointer;
  width: 80px;
  text-align: center;
}

.arrow {
  content: "\E025";
  display: inline-block;
  font-family: "space_icon";
  line-height: 1;
  letter-spacing: 0;
  vertical-align: middle;
  position: absolute;
  top: 50%;
  height: 11px;
  margin-top: -5.5px;
  right: 9px;
  font-size: 11px;
  color: #444;
  /* transition: transform 0.5s ease-in-out; */
  transition: transform 0.5s;
}
</style>
