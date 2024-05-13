<!-- @format -->

<template>
  <div>
    <div class="inner-box">
      <span class="back" @click="() => this.$router.go(-1)"><v-icon>mdi-arrow-left</v-icon>뒤로가기</span>
      <div class="main">
        <!-- title -->
        <div>
          <input class="title" type="text" placeholder="제목을 입력해주세요." v-model="notice.title" />
        </div>
        <hr />
        <!-- 본문 영역 -->
        <textarea class="content" style="width: 100%" placeholder="본문을 작성해주세요" v-model="notice.content"> </textarea>
        <!-- update/delete -->
        <hr />
      </div>
      <button class="crud" style="float: right" @click="noticeUpdate()">수정</button>
    </div>
  </div>
</template>
<!-- @format -->

<script>
import { detail, update } from "@/api/notice.js";

export default {
  name: "NoticeUpdate",
  components: {},
  data() {
    return {
      userinfo: "", // 오류메시지 제거용
      notice: {},
      notice_no: this.$route.params.notice_no,
    };
  },
  created() {
    detail(this.notice_no, ({ data }) => {
      console.log(data.notice);
      this.notice = data.notice;
    });
  },
  methods: {
    noticeUpdate() {
      update(this.notice_no, this.notice, ({ data }) => {
        if (data === "success") {
          alert("수정 완료");
        } else {
          alert("수정 실패");
        }
        this.$router.push("/notice");
      }),
        (error) => {
          console.log(error);
        };
    },
  },
};
</script>

<style scoped>
.inner-box {
  margin: auto;
  margin-top: 100px;
  margin-bottom: 100px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  min-width: 900px;
  max-width: 1200px;
  border-radius: 4px;
  border: solid 1px #d9d9d9;
  padding: 30px 100px 100px 30px;
}
.back {
  cursor: pointer;
  color: #d9d9d9;
}
.back:hover {
  color: #939393;
}
.main {
  margin-top: 60px;
  margin-left: 60px;
  padding: 0;
}
.title {
  font-size: 28px;
  font-weight: bold;
  width: 100%;
  border: none;
}
.content {
  border: none;
  height: 500px;
  padding: 20px;
  font-size: 18px;
  width: 100%;
  padding: 1%;
}

hr {
  background: #d9d9d9;
  height: 1px;
  border: 0;
  display: block;
  margin: 10px 0 10px 0;
}
</style>
