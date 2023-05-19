<template>
  <div>
    <div class="option-window-container">
      <a class="option-title" @click="$emit('toggleOptionWindow')">
        {{ optionName }}
        <span class="arrow" :class="{ clicked: isOpen }">
          <img src="@/assets/icon/optionarrow.svg" />
        </span>
      </a>
      <v-date-picker
        v-model="picker"
        type="month"
        color="rgba(255, 0, 0, 0.5)"
        max="2022-04"
        class="date-window"
        v-show="isOpen"
      />
      <div></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ApartmentItemOptionDate",
  components: {},
  data() {
    return {
      picker: new Date(new Date("2022-12-31") - new Date().getTimezoneOffset() * 60000).toISOString().substring(0, 10),
    };
  },
  created() {},
  methods: {},
  props: {
    optionName: String,
    isOpen: Boolean,
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

.date-window {
  min-width: 290px;
  position: absolute;
  top: 50px;
  left: 0;
  z-index: 100;
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
