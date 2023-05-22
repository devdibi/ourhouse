<template>
  <div class="background">
    <div id="inputcontainer">
      <legend id="title">비밀번호 재발급</legend>
      <input
        type="email"
        id="email"
        class="textbox"
        v-model="user.email"
        placeholder="이메일"
      />
      <input
        type="text"
        id="name"
        class="textbox"
        v-model="user.name"
        placeholder="이름"
      />
      <button id="submitbutton" class="button" @click="password">
        임시 비밀번호 발급
      </button>
    </div>
  </div>
</template>

<script>
import { randomPassword } from "@/api/user";

export default {
  name: "userFindPassword",
  components: {},
  data() {
    return {
      user: {
        email: "",
        name: "",
      },
    };
  },
  created() {},
  methods: {
    password() {
      if (this.user.email == "" || this.user.name == "") {
        alert("이메일과 이름을 입력해주세요");
        return;
      }
      randomPassword(
        this.user.email,
        this.user.name,
        ({ data }) => {
          console.log(data);
          if (data.message == "fail") {
            alert("다시 시도해주세요");
          } else if (data.compareResult == "fail") {
            alert("일치하는 유저 정보가 없습니다.");
          } else {
            alert("이메일로 비밀번호 전송 완료");
            this.$router.push({ name: "login" });
          }
        },
        (error) => {
          console.log(error);
        }
      );
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

#inputcontainer {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  width: 480px;
  height: 450px;
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

#name {
  top: 190px;
}

#submitbutton {
  top: 300px;
  background: rgba(217, 217, 217, 0.6);
  color: #939393;
}

#submitbutton:hover {
  background: #6960d5;
  color: #ffffff;
}
</style>