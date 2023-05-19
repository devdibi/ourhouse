<template>
  <div class="joinform">
    <form action="submit" id="joinform">
      <legend id="title">회원가입</legend>
      <input type="email" id="email" class="textbox" v-model="user.email" placeholder="이메일">
      <input type="password" id="password" class="textbox" v-model="user.password" placeholder="비밀번호">
      <input type="password" id="passwordcheck" class="textbox" v-model="passwordcheck" placeholder="비밀번호 확인">
      <input type="text" id="name" class="textbox" v-model="user.name" placeholder="이름">
      <input type="number" id="age" class="textbox" v-model="user.age" placeholder="나이">
      <button type="submit" id="joinbutton" class="button">회원가입</button>
      <label class="gender" >
        <input type="radio" name="gender1" value="1" v-model="user.gender">
        <div id="gender1">남</div>
      </label>
      <label class="gender">
        <input type="radio" name="gender2" value="2" v-model="user.gender">
        <div id="gender2">여</div>
      </label>
        <div id="likearea">관심지역(선택)</div>
        <div>
          <select name="likeareasSido" id="likeareasSido" class="likeareas" @change="selectSido">
            <option value="">시도 선택</option>
            <option v-for="(item, index) in sidos" :key="index" :value="item.sidoCode">{{ item.name }}</option>
          </select>
          <select name="likeareasGungu" id="likeareasGungu" class="likeareas">
            <option value="">시군구 선택</option>
            <option v-for="(item, index) in sigungus" :key="index" :value="item.sigunguCode">{{ item.name }}</option>
          </select>
          <select name="likeareasDong" id="likeareasDong" class="likeareas">
            <option value="">동코드</option>
            <option value="">임시값</option>
            <option value="">임시값</option>
          </select>
      </div>

      <div id="dwellarea">거주지역(선택)</div>
        <div>
          
          <select name="dwellareasSido" id="dwellareasSido" class="dwellareas">
            <option value="">시도</option>
            <option value="">임시값</option>
            <option value="">임시값</option>
          </select>
          <select name="dwellareasGungu" id="dwellareasGungu" class="dwellareas">
            <option value="">시군구</option>
            <option value="">임시값</option>
            <option value="">임시값</option>
          </select>
          <select name="dwellareasDong" id="dwellareasDong" class="dwellareas">
            <option value="">동코드</option>
            <option value="">임시값</option>
            <option value="">임시값</option>
          </select>
      </div>
    </form>
  </div>
</template>

<script>
// import { getSido, getSigungu, getDong } from "@/api/user";
import { getSido, getSigungu } from "@/api/user";

export default {
  name: 'userJoin',
  components: {},
  data() {
    return {
      passwordcheck: "",
      user: {
        email: "",
        password: "",
        name: "",
        age: "",
        gender: "",
      },
      sidos: [],
      sidoCode:"",
      sigungus: [],
      sigungucode: "",
      dongs: [],
      dongCode:""
    };
  },
  created() { 
    getSido(({ data }) => { 
      console.log("시도 얻어오기")
      if (data.message == "success") {
        this.sidos = data.sidoList;
      }
      else { 
        console.log("axios fail")
      }
    }, (error) => { 
      console.log(error);
    })
  },
  methods: {
    selectSido() { 
      getSigungu(this.sidoCode, ({ data }) => { 
        console.log(data)
      }, (error) => { console.log(error); }
      )
    }
  },

};
</script>

<style scoped>
* {
  border: none;
  padding: 0;
  margin: 0;

  font-family: 'Noto Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
}

.textbox {
  position: absolute;
  left: 60px;
  width: 360px;
  height: 60px;
  background: #FFFFFF;
  border-bottom: 1px solid #D9D9D9;
}

.textbox::placeholder {
  color: #D9D9D9;
}

.button {
  position: absolute;
  left: 65px;
  width: 350px;
  height: 60px;
  border-radius: 5px;
}

#joinform {
  position: relative;
  width: 480px;
  height: 775px;
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

#passwordcheck {
  top: 260px;
}

#name {
  top: 330px;
}

#age {
  top: 400px;
  width: 170px;
}

.gender input[type="radio"] {
  display: none;
}

.gender input[type="radio"]+div {
  position: absolute;
  top: 420px;
  display: inline-block;
  text-align: center;
  line-height: 40px;

  width: 70px;
  height: 40px;
  background: #E8E8E8;
  color: #989898;
}

#gender1 {
  border-radius: 5px 0px 0px 5px;
  left: 270px;
}

#gender2 {
  left: 340px;
  border-radius: 0px 5px 5px 0px;
  border-left: 1px solid rgba(152, 152, 152, 0.8);
}

#likearea{
  position:absolute;
  top: 490px;
  color: #D9D9D9;
  left:58px;
}

.likeareas{
  position: absolute;
  top: 520px;
  left:58px;
}

#dwellarea{
  position:absolute;
  top: 570px;
  color: #D9D9D9;
  left:58px;
}

.dwellareas{
  position: absolute;
  top: 600px;
  left:58px;
}

#joinbutton {
  top: 655px;
  background: rgba(217, 217, 217, 0.6);
  color: #939393;
}

#joinbutton:hover {
  background: #6960D5;
  color: #FFFFFF;
}
</style>