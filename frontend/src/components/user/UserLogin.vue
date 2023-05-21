<template>
  <div class="background">
    <form action="submit" id="loginform">
      <legend id="title">로그인</legend>
      <input
        type="email"
        id="email"
        class="textbox"
        v-model="user.email"
        placeholder="이메일"
      />
      <input
        type="text"
        id="password"
        class="textbox"
        v-model="user.password"
        placeholder="비밀번호"
      />
      <button type="submit" id="loginbutton" class="button">로그인</button>
      <button id="joinbutton" class="button" @click="join">회원가입</button>
      <div id="findpassworddiv">
        비밀번호를 잊으셨나요?
        <router-link to="/user/findpassword" id="findpassword"
          >비밀번호 찾기</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "userLogin",
  components: {},
  data() {
    return {
      user: {
        email: "",
        password: "",
      },
    };
  },
  created() {},
  methods: {
    join() {
      this.$router.push({ name: "join" });
    },
  },
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
  background: #ffffff;
  border-bottom: 1px solid #d9d9d9;

  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

.textbox::placeholder {
  color: #d9d9d9;
}

.button {
  position: absolute;
  left: 65px;
  width: 350px;
  height: 60px;
  border-radius: 5px;
  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

#loginform {
  position: relative;
  width: 480px;
  height: 500px;
  display: inline-block;
  text-align: center;
  background: #ffffff;
  background-blend-mode: color-dodge;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 1px 2px rgba(0, 0, 0, 0.3),
    0px 1px 3px 1px rgba(0, 0, 0, 0.15);
  border-radius: 16px;
}

#title {
  position: absolute;
  top: 30px;
  font-family: "Noto Sans";
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

#password {
  top: 190px;
}

#joinbutton {
  top: 360px;
  background: #6960d5;
  color: #ffffff;
}

#joinbutton:hover {
  background: #8d87d6;
}

#loginbutton {
  top: 280px;
  background: rgba(217, 217, 217, 0.6);
  color: #939393;
}

#loginbutton:hover {
  background: #6960d5;
  color: #ffffff;
}

#findpassworddiv {
  position: absolute;
  top: 430px;
  left: 204px;
  font-family: "Noto Sans";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 16px;
  text-align: center;
  color: #000000;
}

#findpassword {
  color: #5748ff;
  text-decoration-line: none;
}
</style>