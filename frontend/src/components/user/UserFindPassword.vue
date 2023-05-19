<template>
  <div class="background">
    <form action="submit" id="loginform">
      <legend id="title">비밀번호 찾기</legend>
      <input type="email" id="email" class="textbox" v-model="user.email" placeholder="이메일">
      <input type="text" id="name" class="textbox" v-model="user.name" placeholder="이름">
      <button type="submit" id="submitbutton" class="button">임시 비밀번호 발급</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'userFindPassword',
  components: {},
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  created() { },
  methods: {},
};
</script>

<style scoped>
* {
  border: none;
}

.textbox {
  position: absolute;
  left: 60px;
  width: 360px;
  height: 50px;
  background: #FFFFFF;
  border-bottom: 1px solid #D9D9D9;

  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

.textbox::placeholder{
  color: #D9D9D9;
}

.button {
  position: absolute;
  left: 65px;
  width: 350px;
  height: 60px;
  border-radius: 5px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

#loginform {
  position: relative;
  width: 480px;
  height: 450px;
  display: inline-block;
  text-align: center;
  background: #FFFFFF;
  background-blend-mode: color-dodge;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 1px 2px rgba(0, 0, 0, 0.3), 0px 1px 3px 1px rgba(0, 0, 0, 0.15);
  border-radius: 16px;
}

#title {
  position: absolute;
  top: 30px;
  left: 136px;
  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 36px;
  line-height: 49px;
  color: #000000;
  text-align: center;
}

#email {
  top: 120px;
}

#name {
  top: 190px;
}

#submitbutton {
  top: 280px;
  background: rgba(217, 217, 217, 0.6);
  color: #939393;
}

#submitbutton:hover{
  background: #cccccc;
  color: #4b4b4b;
}
</style>