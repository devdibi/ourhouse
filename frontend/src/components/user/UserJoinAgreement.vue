<template>
  <div id="box">
    <div>
      <div>
        <div class="title" id="title1">
          개인정보 수집 이용 동의 <span class="sub">(필수)</span>
        </div>
        <div>
          <b-form-textarea id="textarea1" class="textarea" v-model="text1" rows="3" max-rows="6"
            readonly></b-form-textarea>
        </div>
        <div id="checkbox1">
          <b-form-checkbox v-model="status1" value="동의함" unchecked-value="동의하지 않음">
            {{ status1 }}
          </b-form-checkbox>
        </div>
      </div>
      <div>
        <div class="title" id="title2">
          마케팅 정보 수신 동의 <span class="sub">(선택)</span>
        </div>
        <div>
          <b-form-textarea id="textarea2" class="textarea" v-model="text2" rows="3" max-rows="6"
            readonly></b-form-textarea>
        </div>
        <div id="checkbox2">
          <b-form-checkbox v-model="status2" value="동의함" unchecked-value="동의하지 않음">
            {{ status2 }}
          </b-form-checkbox>
        </div>
        <div id="checkbox3">
          개인정보 수집 및 이용, 마케팅 정보 수신(선택)에 모두 동의합니다.
        </div>
        <div id="checkbox4">
          <b-form-checkbox v-model="status3" value="동의함" unchecked-value="동의하지 않음" @change="checkAll()">
            {{ status3 }}
          </b-form-checkbox>
        </div>
        <input type="button" id="previousbtn" value="이전" class="button" @click="previous">
        <input type="button" id="nextbtn" value="다음" class="button" @click="next">
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
  left: 35px;
  font-family: 'Noto Sans';
  font-style: normal;
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

.title {
  position: absolute;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 24px;
  line-height: 33px;
  color: #000000;
}

#title1 {
  top: 32px;
}

#title2 {
  top: 240px;
}

.sub {
  font-weight: 400;
  font-size: 18px;
  line-height: 25px;
}

.textarea {
  position: absolute;
  width: 730px;
  height: 100px;
  background: #ffffff;
}

#textarea1 {
  top: 78px;
}

#textarea2 {
  top: 285px;
}

#checkbox1 {
  position: absolute;
  top: 173px;
  left: 630px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  color: #000000;
}

#checkbox2 {
  position: absolute;
  padding: 0;
  top: 380px;
  left: 630px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  color: #000000;
}

#checkbox3 {
  position: absolute;
  top: 420px;
  left: 410px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  color: #000000;
}

#checkbox4 {
  position: absolute;
  top: 440px;
  left: 630px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  color: #000000;
}

#previousbtn{
  left:35px;
}
#nextbtn{
  left:415px;
}

.button {
  position: absolute;
  border: 0;
  width: 350px;
  height: 60px;
  background: #e8e8e8;
  border-radius: 5px;
  top: 490px;

  font-family: "Noto Sans";
  font-style: normal;
  font-size: 16px;
  line-height: 22px;
  text-align: center;
  color: #989898;
}

.button:hover{
  background: #6960d5;
  color: #ffffff;
}
</style>