<template>
    <Layout>
        <Content>
            <div class="user_content">
                <div class="Card">
                    <div class="UserCoverEditor">
                        <div class="userCover">
                            <img class="userCover-image" :src="user.picture"/>
                        </div>
                    </div>
                     
                    <div class="user_information">
                        <div class="user_information_left">
                            <div>
                                <Avatar shape="square" :src="user.avatar" style="width:160px; height:160px"/>
                            </div>
                        </div>
                        <div class="user_information_right">
                            <div style="width: 400px; float:left">
                                <div>
                                    <span style="font-weight:bold; font-size:24px">{{user.username}}</span>
                                </div>
                                <Divider style="margin:10px 0"/>
                                <div>
                                    <span style="font-size:16px">电话号码：{{user.phoneNumber}}</span></br>
                                    <span style="font-size:16px">地址：{{user.address}}</span></br>
                                    <span style="font-size:16px">个人简介：{{user.biography}}</span></br>
                                </div>
                            </div>
                            <div style="float: right; margin-right:50px; text-align:center; margin-top:20px">
                                <div style="margin-bottom:50px;font-size:14px">
                                   <div style="float:left">
                                       <div>关注了</div>
                                       <div>{{user.followersNumber}}</div>
                                   </div>
                                   <Divider type="vertical" />
                                   <div style="float:right">
                                       <div>关注者</div>
                                       <div>{{user.followingNumber}}</div>
                                   </div>
                                </div>
                                
                                <div style="text-align:center">
                                    <router-link :to="{name: 'updateUser'}">
                                        <Button type="primary" ghost >编辑个人资料</Button>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
               
            </div>

            <div class="page_content">
                    <div style="text-align:center; font-size:16px;padding-top:15px">
                         <Row>
                            <Col span="12">
                                <Row>
                                    <Col span="6">
                                        <a @click="myPulish(user.id)" style="color:gray">我的发布</a>
                                    </Col>
                                    <Col span="6">
                                        <a @click="myAdopt(user.id)" style="color:gray">我的领养</a>
                                    </Col>
                                    <Col span="6">
                                        <a @click="myCollection(user.id)" style="color:gray">我的收藏</a>
                                    </Col>
                                    <Col span="6">
                                        <a @click="myArticles(user.id)" style="color:gray">我的帖子</a>
                                    </Col>
                                </Row>
                            </Col>
                            <Col span="6"></Col>
                            <Col span="6"></Col>
                        </Row>
                    </div>
                     <Divider style="margin-top:15px"/>
                     
                    <dogeCard v-if="dogeList" v-bind:item="doge" v-for="(doge,index) in dogeList" :key="index"></dogeCard>
                    <forumCard v-if="forumList"  v-bind:item="article" v-for="(article,index) in forumList" :key="index"></forumCard>
                </div>
        </Content>
    </Layout>
</template>

<script>
import dogeCard from '@/components/dogeCard' //引入dogeCard组件
import forumCard from '@/components/forumCard' //引入dogeCard组件
    export default {
        name: "userPageContent",
        data() {
            return {
                dogeList:"",
                forumList:''
            }
        },
        computed: {
            user () {
                return this.$store.state.user
            }
        },
        components:{
            'dogeCard': dogeCard,
            'forumCard': forumCard
        },
        methods: {
            myPulish(fosterId){
                this.axios.get("http://localhost:80/listDogsByFosterId",{
                    params:{
                        fosterId: fosterId
                    }
                })
                .then(resp => {
                    this.dogeList = resp.data.data
                    this.forumList = null
                })
                .catch(err => {
                    this.$Message.error("请求出错");
                });
            },
            myAdopt(adoptId){
                this.axios.get("http://localhost:80/listDogsByAdoptId",{
                    params:{
                        adoptId: adoptId
                    }
                })
                .then(resp => {
                    this.dogeList = resp.data.data
                    this.forumList = null
                })
                .catch(err => {
                    this.$Message.error("请求出错");
                });
            },
            myCollection(collectionId){
                this.axios.get("http://localhost:80/listDogsByCollectionId",{
                    params:{
                        collectionId: collectionId
                    }
                })
                .then(resp => {
                    this.dogeList = resp.data.data
                    this.forumList = null
                })
                .catch(err => {
                    this.$Message.error("请求出错");
                });
            },
            myArticles(userId){
                this.axios.get("http://localhost:80/listArticleByUserId",{
                    params:{
                        userId: userId
                    }
                })
                .then(resp => {
                    this.dogeList = null
                    this.forumList = resp.data.data
                })
                .catch(err => {
                    this.$Message.error("请求出错");
                });
            }
        },
        mounted: function () {
            this.myPulish(this.$store.state.user.id)
        }
    }
</script>

<style>
    .user_content {
        margin: auto;
        margin-top: 10px;
        width: 70%;
    }
   
    .userCover{
        border-top-right-radius: 1px;
        border-top-left-radius: 1px;
    }
    .userCover-image{
        width:100%; 
        height: 240px;
        object-fit: cover;
    }
    .Card {
        background: #fff;
        overflow: hidden;
        border-radius: 2px;
        box-sizing: border-box;
    }
    .UserCoverEditor {
        position: relative;
    }

    .user_information {
        height: 160px;
    }
    .user_information_left {
        width: 20%;
        text-align: center;
        margin-top: -25px;
        float: left;
    }
    .user_information_right {
        width: 80%;
        float: right;
    }
</style>
