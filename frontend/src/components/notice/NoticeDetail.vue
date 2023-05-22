<template>
    <div>
        <div class="inner-box">
            <div>
                <span class="title">{{ notice.title}}</span>
            </div>
            <div class="meta" >
                <span style="margin-right:10px">{{notice.email}}</span>
                <span v-if="notice.updateDate == null">
                    {{notice.createDate}}
                </span>
                <span v-else>
                    {{notice.updateDate}} (수정됨)
                </span>
                <span style="float:right">조회수 {{ notice.hit }}</span>
            </div>
            <v-divider></v-divider>

            <!-- 본문 영역 -->
            <div class="content" >
                {{notice.content}}
            </div>
            <div class="ud">
                <router-link :to="{name:'noticeupdate',params:{notice_no:this.noticeNo}}" class="ud-content">수정</router-link> | 
                <router-link :to="{name:'noticedelete'}" class="ud-content">삭제</router-link>
            </div>
            <v-divider></v-divider>

        </div>
    </div> 
</template>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans&display=swap" rel="stylesheet"/>
<script>
import { detail } from "@/api/notice.js";

export default {
    name: 'noticeDetail',
    components: {},
    data() {
        return {
            notice: {},
            comments: [],
            noticeNo: this.$route.params.notice_no,
            comment: {
                noticeNo: 0,
                comment: "",
                email: "",
                commentId: 0
            }
        };
    },
    created() {
        detail(this.noticeNo, ({data}) => {
            this.notice = data.notice;
            this.comments = data.comments;
        })

    },
    methods: {
        
    },
};
</script>

<style scoped>
.inner-box{
    margin:auto;
    margin-top:100px;
    margin-bottom:100px;
    box-shadow:0px 4px 4px rgba(0, 0, 0, 0.25);
    min-width:900px;
    max-width:1200px;
	border-radius:4px;
    border:solid 1px #d9d9d9;
    padding:100px
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