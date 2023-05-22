<template>
  <div id="box">
    <div>
      <div>
        <div class="title">
          개인정보 수집 이용 동의 <span class="sub">필수</span>
        </div>
        <div>
          <b-form-textarea id="textarea1" v-model="text1" rows="3" max-rows="6"></b-form-textarea>
          <pre class="mt-3 mb-0">{{ text1 }}</pre>
        </div>
        <b-form-checkbox id="checkbox1" v-model="status1" value="동의함" unchecked-value="동의하지 않음">
          {{ status1 }}
        </b-form-checkbox>
      </div>
      <div>
        <div class="title">
          마케팅 정보 수신 동의 <span class="sub">선택</span>
        </div>
        <div>
          <b-form-textarea id="textarea2" v-model="text2" rows="3" max-rows="6"></b-form-textarea>
          <pre class="mt-3 mb-0">{{ text2 }}</pre>
        </div>
        <b-form-checkbox id="checkbox2" v-model="status2" value="동의함" unchecked-value="동의하지 않음">
          {{ status2 }}
        </b-form-checkbox>
        <b-form-checkbox id="checkbox3" v-model="status3" value="동의함" unchecked-value="동의하지 않음" @change="checkAll()">
          {{ status3 }}
        </b-form-checkbox>
        <input type="button" value="이전" class="button" @click="previous">
        <input type="button" value="다음" class="button" @click="next">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "userJoinAgreement",
  components: {},
  data() {
    return {
      status1: "동의하지 않음",
      status2: "동의하지 않음",
      status3: "전체 동의",
      text1: "정보 동의 임시 텍스트",
      text2: "마케팅 정보 동의 임시 텍스트",
    };
  },
  created() { },
  methods: {
    checkAll() {
      this.status1 = "동의함";
      this.status2 = "동의함";
      console.log(this.status1);
    },
    previous(e) {
      e.preventDefault();
      this.$router.go(-1);
    },
    next(e) {
      e.preventDefault();
      if (this.status1 != "동의함") {
        alert("개인정보 수집 이용 동의가 필요합니다.");
      } else {
        this.$router.push({ path: "joinform" });
      }
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

#box {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  width: 800px;
  height: 600px;
  display: inline-block;
  text-align: center;
  background: #ffffff;
  background-blend-mode: color-dodge;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 1px 2px rgba(0, 0, 0, 0.3),
    0px 1px 3px 1px rgba(0, 0, 0, 0.15);
  border-radius: 16px;
}

.button {
  border: 0;
  /* position: absolute; */
  width: 350px;
  height: 60px;
  /* left: 975px;
  top: 714px; */
  background: #e8e8e8;
  border-radius: 5px;

  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
  text-align: center;
  color: #989898;
}
</style>