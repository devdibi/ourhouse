<!-- @format -->

<template>
  <div>
    <div class="inner-box">
      <span class="back" @click="() => this.$router.go(-1)"><mdicon name="arrow-left" />뒤로가기</span>
      <div class="main">
        <!-- title -->
        <div>
          <span class="title">{{ board.title }}</span>
        </div>
        <!-- meta-data -->
        <div class="meta">
          <span style="margin-right: 10px">{{ board.email }}</span>
          <span v-if="board.updateDate == null">{{ board.createDate }}</span>
          <span v-else> {{ board.updateDate }} (수정됨) </span>
          <span style="float: right">조회수 {{ board.hit }}</span>
        </div>
        <hr />
        <!-- 본문 영역 -->
        <div class="content">
          {{ board.content }}
        </div>
        <!-- update/delete -->
        <!-- 작성자만 수정 가능하도록 -->
        <div class="ud" v-if="this.userinfo != 'admin'">
          <span class="ud-content" @click="update()" style="cursor: pointer">수정</span> |
          <span class="ud-content" @click="remove()" style="cursor: pointer">삭제</span>
        </div>

        <hr />
        <!-- 댓글 영역 -->
        <div style="margin-top: 10px; margin-left: 10px">
          <div style="font-size: 18px; font-weight: bold">댓글</div>
          <!-- 댓글 작성 영역 -->
          <div style="max-height: 80px; margin-left: 10px; max-width: 1000px">
            <span>
              <textarea class="comment-write" v-model="comment.comment"></textarea>
            </span>
            <!-- button -->
            <span>
              <button class="crud" style="height: 50px" @click="addComment()">작성</button>
            </span>
          </div>
          <!-- 댓글 리스트 영역 -->
          <div class="comment-list" v-for="item in comments" :key="item.comment_no">
            <!-- 댓글 작성자 -->
            <span style="display: block; font-size: 18px; margin-bottom: 3px">
              {{ item.email }}
            </span>
            <!-- 댓글 내용 -->
            <span style="display: inline-block; margin-left: 10px">
              {{ item.comment }}
            </span>
            <!-- 댓글 삭제 버튼 -->
            <span style="float: right; display: inline-block; margin-right: 5px; color: #939393">
              <button @click="removeComment(item)" style="border: none; background: none; color: #939393">삭제</button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { detail, addComment, removeComment, remove } from "@/api/board.js";

export default {
  name: "BoardDetail",
  components: {},
  data() {
    return {
      userinfo: "", // 오류메시지 제거용
      board: {},
      comments: [],
      boardNo: this.$route.params.board_no,
      comment: {
        boardNo: 0,
        comment: "",
        email: "",
        commentId: 0,
      },
    };
  },
  created() {
    detail(this.boardNo, ({ data }) => {
      this.board = data.board;
      this.comments = data.comments;
    });
  },
  methods: {
    addComment() {
      // 리스트에 추가할 comment 객체 생성
      let addList = {
        boardNo: this.$route.params.board_no,
        comment: this.comment.comment,
        email: "test", // 더미데이터(추후 유저가 완료되면 수정해야함)
      };

      // db 반영
      addComment(
        addList.boardNo,
        addList,
        ({ data }) => {
          if (data === "success") {
            alert("등록이 완료되었습니다.");

            // text area 정리
            this.comment.comment = "";

            // 리스트 갱신
            detail(this.boardNo, ({ data }) => {
              this.board = data.board;
              this.comments = data.comments;
            });
          } else {
            alert("등록에 실패하였습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    // 유저 개발 시 삭제 권한 선정
    removeComment(item) {
      if (this.userinfo != item.email || this.userinfo === "admin") {
        removeComment(item.commentNo, ({ data }) => {
          if (data === "success") {
            // 리스트 갱신
            detail(item.boardNo, ({ data }) => {
              this.board = data.board;
              this.comments = data.comments;
            });

            alert("댓글의 삭제가 완료되었습니다.");
          } else {
            alert("댓글 삭제에 실패하였습니다.");
          }
        });
        console.log("remove");
      } else {
        alert("삭제 권한이 없습니다.");
      }
    },
    remove() {
      if (this.userinfo != this.board.email) {
        remove(this.boardNo, ({ data }) => {
          if (confirm("삭제하시겠습니까?")) {
            if (data === "success") {
              alert("삭제가 완료되었습니다.");
              this.$router.push("/board");
            } else {
              alert("삭제에 실패했습니다.");
            }
          }
        });
      } else {
        alert("삭제 권한이 없습니다.");
      }
    },
    update() {
      if (this.userinfo != this.board.email) {
        this.$router.push(`/board/update/${this.boardNo}`);
      } else {
        alert("수정 권한이 없습니다.");
      }
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
}
.meta {
  font-size: 14px;
  color: #939393;
  margin: 10px 0 5px 10px;
}
.content {
  height: 500px;
  padding: 20px;
  font-size: 18px;
}

.ud {
  text-align: right;
  font-size: 14px;
  margin-right: 5px;
  margin-bottom: 5px;
  color: #939393;
}
.ud-content {
  text-decoration: none;
  padding: 5px;
  cursor: pointer;
}
.ud-content:hover {
  color: #6f6f6f;
}

hr {
  background: #d9d9d9;
  height: 1px;
  border: 0;
  display: block;
}

/* 댓글 영역 */

.comment-write {
  padding: 1%;
  vertical-align: middle;
  border: solid 1px #f3f3f3;
  margin: 10px;
  min-width: 85%;
  height: 50px;
}

.comment-list {
  padding: 10px;
  margin-bottom: 10px;
  margin-left: 20px;
  margin-right: 5%;
  font-size: 12px;
  background-color: #f3f3f3;
  max-width: 1000px;
  min-width: 670px;
}
.crud {
  background-color: rgba(67, 56, 202, 0.8);
  color: #ffffff;
  width: 80px;
  height: 35px;
  border-radius: 4px;
  font-size: 15px;
  box-shadow: 0px 0px 3px 0px black;
  border: none;
}
</style>
