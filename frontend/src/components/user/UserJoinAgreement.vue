<template>
  <div>
    <form action="" id="agreementform">
      <legend id="title">로그인</legend>
      <input type="email" id="email" class="textbox" v-model="user.email" placeholder="이메일">
      <input type="text" id="password" class="textbox" v-model="user.password" placeholder="비밀번호">
      <button type="submit" id="loginbutton" class="button">로그인</button>
      <button id="joinbutton" class="button">회원가입</button>
      <div id="findpassworddiv">비밀번호를 잊으셨나요? <a href="" id="findpassword">비밀번호 찾기</a></div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'userJoinAgreement',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>