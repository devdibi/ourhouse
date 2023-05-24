<template>
  <div>
    <div id="container">
      <h2 id="title">유저 목록</h2>
      <!-- <b-table :items="userList" id="table"></b-table> -->
      <table>
        <thead>
          <tr>
            <th>이메일</th>
            <th>이름</th>
            <th>성별</th>
            <th>나이</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in userList" :key="user">
            <td>{{ user.email }}</td>
            <td>{{ user.name }}</td>
            <td v-if="user.gender == 1">남성</td>
            <td v-if="user.gender == 2">여성</td>
            <td>{{ user.age }}</td>
            <td>
              <input
                type="button"
                value="유저 삭제"
                @click="deleteUser(user.email)"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { API } from "@/api/axios-jwt";

export default {
  name: "adminList",
  components: {},
  data() {
    return {
      userList: [],
    };
  },
  created() {
    console.log("관리자 페이지");
    let http = API();
    http
      .get("/admin/loadAllUsers")
      .then(({ data }) => {
        if (data.message == "success") {
          console.log(data);
          this.userList = data.userInfo;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    deleteUser(userEmail) {
      console.log("유저 삭제 : " + userEmail);
      let http = API();
      http
        .delete("/admin/" + userEmail)
        .then(({ data }) => {
          if (data == "success") {
            console.log(data);
            this.userList = data.userInfo;
            alert("삭제 성공");
            // this.$router.go(0);

            //성공시 데이터 리로드
            http
              .get("/admin/loadAllUsers")
              .then(({ data }) => {
                if (data.message == "success") {
                  console.log(data);
                  this.userList = data.userInfo;
                }
              })
              .catch((err) => {
                console.log(err);
              });
          }
        })
        .catch((err) => {
          console.log(err);
        });
      console.log(userEmail);
    },
  },
};
</script>

<style scoped>
#aptContainer {
  width: 100%;
  margin: auto;
  padding-top: 70px;
}

#table {
  max-width: 1000px;
  margin: auto;
  text-align: center;
}

#title {
  text-align: center;
  margin-bottom: 40px;
}
</style>