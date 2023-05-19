<template>
    <div>
        <div class="inner-box">
            <!-- 제목 영역 -->
            <div>
                <span >
                    <input class="title" type="text" placeholder="제목을 입력해주세요." v-model="board.title"/>
                </span>
            </div>
            <v-divider></v-divider>

            <!-- 본문 영역 -->
            <div class="content-frame" >
                <textarea class="content" placeholder="본문을 작성해주세요" v-model="board.content">
                </textarea>
            </div>
            <v-divider style="margin-bottom:10px"></v-divider>
                <v-btn style="float:right" @click="boardwrite()">글 작성</v-btn>
        </div>
    </div> 
</template>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans&display=swap" rel="stylesheet"/>
<script>
import { write } from '@/api/board.js';

export default {
    name: 'BoardDetail',
    components: {},
    data() {
        return {
            board: {
                boardNo: null,
                content: "",
                createDate: null,
                email: "",
                hit: 0,
                title: "",
                updateDate:null,
            }
        };
    },
    created() {    },
    methods: {
        // 게시글 작성
        boardwrite() {
            // 더미데이터(유저 추가후 수정 필요)
            this.board.email = "test";

            // axios 게시글 작성
            write(
                this.board,
                ({ data }) => {
                    if (data === "success") {
                    alert("등록이 완료되었습니다.")
                    this.moveList();
                    } else {
                    alert("등록을 실패했습니다.")
                    }   
                },
                (error) => {
                    console.log(error);
                }
            );    
        },
        moveList() {
            this.$router.push({ name: 'boardlist' })
        }
    },
};
</script>

<style scoped>
.inner-box{
    margin:auto;
    margin-top:100px;
    box-shadow:0px 4px 4px rgba(0, 0, 0, 0.25);
    min-width:900px;
    max-width:1200px;
    max-height:1080px;
	border-radius:4px;
    border:solid 1px #d9d9d9;
    padding:100px
}
.title { 
    font-size:24px; 
    font-weight: bold;
    width:100%;
    padding:1%
}
.meta{
    font-size:12px; 
    color:#939393;
     margin: 10px 0 5px 10px
}
.content-frame{
    height:500px;
    padding:20px
}
.content{
    width:100%;
    height:100%;
    padding:1%;
    border-radius:4px;
}

.ud{
    text-align:right; 
    font-size:12px;
    margin-right:5px;
    margin-bottom:5px
}

.comment-write{
    padding:1%;
    vertical-align: middle;
    border:solid 1px #f3f3f3;
    margin:10px;
    width:87%;
}

.comment-list{
    padding:10px;
    margin-bottom:10px; 
    margin-left:10px;
    font-size:12px;
    background-color:#f3f3f3
}
</style>