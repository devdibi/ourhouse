<!-- @format -->

<template>
  <div>
    <div class="inner-box">
      <span class="back" @click="() => this.$router.go(-1)"><mdicon name="arrow-left" /> 뒤로가기</span>
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
      <button class="crud" style="float: right" @click="noticewrite()">작성</button>
    </div>
  </div>
</template>
<script>
import { write } from "@/api/notice.js";

export default {
  name: "NoticeDetail",
  components: {},
  data() {
    return {
      userinfo: "", // 오류메시지 제거용
      notice: {
        noticeNo: 0,
        content: "",
        createDate: "",
        email: "",
        hit: 0,
        title: "",
        updateDate: "",
      },
    };
  },
  created() {},
  methods: {
    // 게시글 작성
    noticewrite() {
      // 더미데이터(유저 추가후 수정 필요)
      this.notice.email = "test";

      // axios 게시글 작성
      write(
        this.notice,
        ({ data }) => {
          if (data === "success") {
            alert("등록이 완료되었습니다.");
            this.moveList();
          } else {
            alert("등록을 실패했습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "noticelist" });
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
