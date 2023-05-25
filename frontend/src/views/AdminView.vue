<template>
  <div>
    <div id="container">
      <h2 id="title">유저 목록</h2>
      <!-- <b-table :items="userList" id="table"></b-table> -->
      <table id="usertable">
        <thead>
          <tr>
            <th>이메일</th>
            <th>이름</th>
            <th>성별</th>
            <th>나이</th>
            <th>유저 삭제 버튼</th>
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
                id="deletebutton"
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
        .delete("/admin/delete/" + userEmail)
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
*{

}
#container {
  width: 100%;
  margin: auto;
  padding-top: 70px;
}

#usertable {
  max-width: 1000px;
  margin: auto;
  text-align: center;
  margin-bottom: 100px;
}

#title {
  text-align: center;
  margin-bottom: 40px;
}

body {
  font: 90%/1.4 system-ui;
  margin: 0;
  font-family: sans-serif;
}
header {
  padding: 7vh 5vw;
  border-bottom: 1px solid #ddd;
}
header h1,
header p {
  margin: 0;
}
aside {
  padding: 7vh 5vw;
}
.primary {
  overflow: auto;
  scroll-snap-type: both mandatory;
  height: 80vh;
}
table {
  border-collapse: collapse;
}
th,
td {
  /* border: 1px solid #aaa; */
  background-clip: padding-box;
  scroll-snap-align: start;
  text-align: center;
}
tbody tr:last-child th,
tbody tr:last-child td {
  border-bottom: 0;
}
thead {
  z-index: 1000;
  position: relative;
}
th,
td {
  padding: 0.6rem;
  min-width: 6rem;
  text-align: left;
  margin: 0;
}
thead th {
  position: sticky;
  top: 0;
  border-top: 0;
  background-clip: padding-box;
}
thead th.pin {
  left: 0;
  z-index: 1001;
  border-left: 0;
}
tbody th {
  background-clip: padding-box;
  border-left: 0;
}
tbody {
  z-index: 10;
  position: relative;
}
tbody th {
  position: sticky;
  left: 0;
}
thead th,
tbody th {
  background-color: #f8f8f8;
}
td, th{
  text-align: center;
}
#deletebutton{
  height: 35px;
  width: 80px;
  background-color: #cac5ff;
  color: white;
}
#deletebutton:hover{
  
  background-color: #7b73d1;
  color: white;
}
</style>