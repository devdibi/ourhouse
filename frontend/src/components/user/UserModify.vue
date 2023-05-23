<template>
  <div id="rootDiv">
    <div id="joinform">
      <legend id="title">개인정보 조회 및 수정</legend>
      <input type="email" id="email" class="textbox" v-model="user.email" readonly />
      <input type="password" id="password" class="textbox" v-model="user.password" placeholder="비밀번호 수정" />
      <input type="password" id="passwordcheck" class="textbox" v-model="passwordcheck" placeholder="수정할 비밀번호 확인" />
      <input type="text" id="name" class="textbox" v-model="user.name" placeholder="이름" />
      <input type="number" id="age" class="textbox" v-model="user.age" placeholder="나이" />

      <b-form-group id="genderbtn">
        <b-form-radio-group id="gender" class="gender" v-model="user.gender" :options="genderOptions"
          buttons></b-form-radio-group>
      </b-form-group>
      <div id="likearea">관심지역 {{ nowFavofiteArea }}</div>
      <div>
        <select name="likeareasSido" id="likeareasSido" class="likeareas sido" @change="selectSido('like')"
          v-model="likeSidoCode">
          <option value="">시도 선택</option>
          <option v-for="(item, index) in likeSidos" :key="index" :value="item.sidoCode">
            {{ item.name }}
          </option>
        </select>
        <select name="likeareasGungu" id="likeareasGungu" class="likeareas sigungu" @change="selectSigungu('like')"
          v-model="likeSigunguCode">
          <option value="">군구 선택</option>
          <option v-for="(item, index) in likeSigungus" :key="index" :value="item.sigunguCode">
            {{ item.name }}
          </option>
        </select>
        <select name="likeareasDong" id="likeareasDong" class="likeareas dong" v-model="likeDongCode">
          <option value="">동 선택</option>
          <option v-for="(item, index) in likeDongs" :key="index" :value="item.dongCode">
            {{ item.name }}
          </option>
        </select>
      </div>

      <div id="dwellarea">거주지역 {{ nowDwellArea }}</div>
      <div>
        <select name="dwellareasSido" id="dwellareasSido" class="dwellareas sido" @change="selectSido('dwell')"
          v-model="dwellSidoCode">
          <option value="">시도 선택</option>
          <option v-for="(item, index) in dwellSidos" :key="index" :value="item.sidoCode">
            {{ item.name }}
          </option>
        </select>
        <select name="dwellareasGungu" id="dwellareasGungu" class="dwellareas sigungu" @change="selectSigungu('dwell')"
          v-model="dwellSigunguCode">
          <option value="">군구 선택</option>
          <option v-for="(item, index) in dwellSigungus" :key="index" :value="item.sigunguCode">
            {{ item.name }}
          </option>
        </select>

        <select name="dwellareasDong" id="dwellareasDong" class="dwellareas dong" v-model="dwellDongCode">
          <option value="">동 선택</option>
          <option v-for="(item, index) in dwellDongs" :key="index" :value="item.dongCode">
            {{ item.name }}
          </option>
        </select>
      </div>
      <button id="joinbutton" class="button" @click="clickModify">
        회원 정보 수정
      </button>
    </div>
  </div>
</template>

<script>
import { getSido, getSigungu, getDong, getUserInfo, updateUserInfo } from "@/api/user";

export default {
  name: "userJoin",
  components: {},
  data() {
    return {
      passwordcheck: "",
      nowDwellArea: "",
      nowFavofiteArea: "",
      user: {
        email: "",
        password: "",
        name: "",
        age: "",
        gender: "",
        dwellArea: "",
        favoriteArea: "",
      },

      likeSidos: [],
      likeSidoCode: "",
      likeSigungus: [],
      likeSigunguCode: "",
      likeDongs: [],
      likeDongCode: "",
      dwellSidos: [],
      dwellSidoCode: "",
      dwellSigungus: [],
      dwellSigunguCode: "",
      dwellDongs: [],
      dwellDongCode: "",

      genderOptions: [
        { text: "남", value: "1" },
        { text: "녀", value: "2" },
      ],
    };
  },
  created() {
    //시도 정보 얻어오기
    getSido(
      ({ data }) => {
        console.log("시도 얻어오기");
        if (data.message == "success") {
          this.likeSidos = data.sidoList;
          this.dwellSidos = data.sidoList;
        } else {
          console.log("axios fail");
        }
      },
      (error) => {
        console.log(error);
      }
    );

    console.log("사용자 정보 얻기")
    getUserInfo(({ data }) => {
      console.log(data);
      //사용자 정보 설정
      this.user.email = data.email;
      this.user.name = data.name;
      this.user.age = data.age;
      this.user.gender = data.gender;
      this.nowDwellArea = data.dwellAreaName;
      this.nowFavofiteArea = data.favoriteAreaName;
    }, (e) => {
      console.log("axios error");
      console.log(e);
    })

  },
  methods: {
    //시도 선택시 시군구 얻어오기
    selectSido(status) {
      if (status == "like") {
        this.likeSigunguCode = "";
        this.likeDongCode = "";
        getSigungu(
          this.likeSidoCode,
          ({ data }) => {
            console.log("시군구 얻어오기");
            this.likeSigungus = data.sidoList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
      if (status == "dwell") {
        this.dwellSigunguCode = "";
        this.dwellDongCode = "";
        getSigungu(
          this.dwellSidoCode,
          ({ data }) => {
            console.log("시군구 얻어오기");
            this.dwellSigungus = data.sidoList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    //시군구 선택시 동 얻어오기
    selectSigungu(status) {
      if (status == "like") {
        this.likeDongCode = "";
        getDong(
          this.likeSigunguCode,
          ({ data }) => {
            console.log("동 얻어오기");
            this.likeDongs = data.dongList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
      if (status == "dwell") {
        this.dwellDongCode = "";
        getDong(
          this.dwellSigunguCode,
          ({ data }) => {
            console.log("동 얻어오기");
            this.dwellDongs = data.dongList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },

    //회원 정보 수정 버튼 누를 시 이벤트
    clickModify() {
      console.log("회원 정보 수정");
      if (this.user.password != this.passwordcheck) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
      }

      //회원 정보 수정 진행
      this.user.dwellArea = this.dwellDongCode;
      this.user.favoriteArea = this.likeDongCode;

      updateUserInfo(this.user, ({ data }) => { console.log(data) }, () => { });
    },
  },
};
</script>

<style scoped>
* {
  border: none;
  padding: 0;
  margin: 0;

  font-family: "Noto Sans";
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
  border-bottom: 1px solid #d9d9d9;
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
}

#joinform {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  width: 480px;
  height: 775px;
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

.gender {
  position: absolute;
  top: 420px;
  display: inline-block;
  text-align: center;
  line-height: 40px;
  padding: none;
  /* width: 140px; */
  /* height: 40px; */
  color: #d9d9d9;
}

#likearea {
  position: absolute;
  top: 490px;
  color: #d9d9d9;
  left: 58px;
}

.likeareas {
  position: absolute;
  top: 520px;
  left: 58px;
  width: 98.49px;
  height: 30px;
}

#dwellarea {
  position: absolute;
  top: 570px;
  color: #d9d9d9;
  left: 58px;
}

.dwellareas {
  position: absolute;
  top: 600px;
  left: 58px;
  width: 98.49px;
  height: 30px;
}

.sigungu {
  left: 183px;
}

.dong {
  left: 308px;
}

#joinbutton {
  top: 655px;
  background: rgba(217, 217, 217, 0.6);
  color: #939393;
}

#joinbutton:hover {
  background: #6960d5;
  color: #ffffff;
}
</style>
