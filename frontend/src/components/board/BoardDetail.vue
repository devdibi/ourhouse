<template>
    <div>
        <div class="inner-box">
            <v-icon @click="back()">mdi-arrow-left</v-icon>
            <div style="padding:50px">
            <div>
                <span class="title">{{ board.title}}</span>
            </div>
            <div class="meta" >
                <span style="margin-right:10px">{{board.email}}</span>
                <span v-if="board.updateDate == null">
                    {{board.createDate}}
                </span>
                <span v-else>
                    {{board.updateDate}} (수정됨)
                </span>
                <span style="float:right">조회수 {{ board.hit }}</span>
            </div>
            <v-divider></v-divider>

            <!-- 본문 영역 -->
            <div class="content" >
                {{board.content}}
            </div>
            <div class="ud">
                <router-link :to="{name:'boardupdate',params:{board_no:this.boardNo}}" class="ud-content">수정</router-link> | 
                <router-link :to="{name:'boarddelete'}" class="ud-content">삭제</router-link>
            </div>
            <v-divider></v-divider>

            <!-- 댓글 영역 -->
            <div style="padding:20px">
                <div style="font-size:18px; font-weight:bold">댓글</div>
                <div style="max-height:80px; margin-letf:10px; max-width:900px">
                    <span>
                        <textarea class="comment-write" v-model="comment.comment"></textarea>
                    </span>
                    <span>
                        <v-btn style="vertical-align: middle;" @click="addComment()">작성</v-btn>
                    </span>
                </div>
                <div class="comment-list" v-for="item in comments" :key="item.comment_no">
                    <!-- 댓글 작성자 -->
                    <span style="display:block; font-size:18px; margin-bottom:3px">
                        {{ item.email}}
                    </span>
                    <!-- 댓글 내용 -->
                    <span style="display:inline-block; margin-left:10px; ">
                        {{ item.comment}}
                    </span>
                    <!-- 댓글 삭제 버튼 -->
                    <span style="float:right; display:inline-block; margin-right:5px; color:#939393">
                        <button @click="removeComment(item)">삭제</button>
                    </span>
                </div>
            </div>

        </div>
        </div>
    </div> 
</template>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans&display=swap" rel="stylesheet"/>
<script>
import { detail, addComment, removeComment } from "@/api/board.js";

export default {
    name: 'BoardDetail',
    components: {},
    data() {
        return {
            board: {},
            comments: [],
            boardNo: this.$route.params.board_no,
            comment: {
                boardNo: 0,
                comment: "",
                email: "",
                commentId: 0
            }
        };
    },
    created() {
        detail(this.boardNo, ({data}) => {
            this.board = data.board;
            this.comments = data.comments;
        })

    },
    methods: {
        back() {
            this.$router.go(-1);
        },
        addComment() {
            // 리스트에 추가할 comment 객체 생성
            let addList = {
                boardNo: this.$route.params.board_no,
                comment: this.comment.comment,
                email: 'test', // 더미데이터(추후 유저가 완료되면 수정해야함)
            }
           
            // db 반영
            addComment(addList.boardNo,addList,
                ({ data }) => {
                    if (data === "success") {
                        alert("등록이 완료되었습니다.");

                        // text area 정리
                        this.comment.comment = "";

                        // 리스트 갱신
                        detail(this.boardNo, ({ data }) => {
                            this.board = data.board;
                            this.comments = data.comments;
                        })
                    }
                    else {
                        alert("등록에 실패하였습니다.")
                    }
                },
                (error) => {
                    console.log(error);
                }
                
            )

            

            
        },
        removeComment(item) {
            console.log(item)
            removeComment(item.commentNo, ({ data }) => {
                if (data === "success") {
                    // 리스트 갱신
                    detail(item.boardNo, ({ data }) => {
                        this.board = data.board;
                        this.comments = data.comments;
                    }) 

                    alert("댓글의 삭제가 완료되었습니다.");
                }
                else {
                    alert("댓글 삭제에 실패하였습니다.")
                }
            })
            console.log('remove')
        }
    },
};
</script>

<style scoped>
.inner-box{
    margin:auto;
    margin-top:50px;
    margin-bottom:100px;
    box-shadow:0px 4px 4px rgba(0, 0, 0, 0.25);
    min-width:900px;
    max-width:1200px;
	border-radius:4px;
    border:solid 1px #d9d9d9;
    padding:20px 100px 100px 20px
}
.title { 
    font-size:24px; 
    font-weight: bold;
}
.meta{
    font-size:12px; 
    color:#939393;
     margin: 10px 0 5px 10px
}
.content{
    height:500px;
    padding:20px
}

.ud{
    text-align:right; 
    font-size:12px;
    margin-right:5px;
    margin-bottom:5px;
    color:#939393
}
.ud-content{
    text-decoration:none;
    color:#939393;
    padding:0px;
    size:10px;
}

.comment-write{
    padding:1%;
    vertical-align: middle;
    border:solid 1px #f3f3f3;
    margin:10px;
    width:87%;
    height:50px
}

.comment-list{
    padding:10px;
    margin-bottom:10px; 
    margin-left:10px;
    font-size:12px;
    background-color:#f3f3f3;
    width:90%
}
</style>