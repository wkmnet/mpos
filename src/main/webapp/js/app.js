/**
 * Created by wkm on 15-11-7.
 */

var bodyApp = angular.module("bodyApp",[],function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
});

//
bodyApp.controller("BodyController",function($http,$scope){

    $scope.leftMenu = {"index":0,"active":"active"};

    $scope.tabShow = {"showNewUser":false,"sendMessage":true};

    $scope.newUserData = {"username":"","email":"","password":""};

    $scope.message = {"respMessage":"","showMessage":false,"showError":false};

    $scope.page = {"pageSize":0,"page":1,"size":5,"pageData":""};

    $scope.switchLeftMenu = function(currentIndex){
        if($scope.leftMenu.index != currentIndex){
            $scope.message.showError = false;
            $scope.message.showMessage = false;
        }
        $scope.leftMenu.index = currentIndex;
        if($scope.leftMenu.index == 2){
            $scope.listUser();
        }
    }

    $scope.tabUserOrList = function(){
        $scope.newUserData = {"username":"","email":"","password":""};
        $scope.message.showError = false;
        $scope.message.showMessage = false;
        $scope.tabShow.showNewUser = !$scope.tabShow.showNewUser;
    }

    $scope.saveUser = function(){
        var data = "username=" + $scope.newUserData.username
                + "&email=" + $scope.newUserData.email
                + "&password=" + $scope.newUserData.password;
        $http.post("/user/register",data).success(function(response){
            var result = response;
            if(result.success){
                $scope.message.showMessage = true;
            } else {
                $scope.message.showError = true;
            }
            $scope.message.respMessage = result.message;
            $scope.newUserData = {"username":"","email":"","password":""};
        });
    }

    $scope.deleteUser = function(userId){
        var data = "id=" + userId;
        $http.post("/user/deleteUser",data).success(function(response){
            var result = response;
            alert(result.message);
            $scope.listUser();
        });
    }


    $scope.listUser = function(){
        var data = "page=" + $scope.page.page
            + "&size=" + $scope.page.size;
        $http.post("/user/listUser",data).success(function(response){
            var result = response;
            $scope.page = result.data;
        });
    }

    $scope.nextPage = function(){
        if($scope.page.page * $scope.page.pageSize >= $scope.page.pageSize){
            alert("没有更多数据");
            return;
        }
        $scope.page.page = $scope.page.page + 1;
        $scope.listUser();
    }

    $scope.prePage = function(){
        if($scope.page.page <= 1){
            alert("不能再往前了");
            return;
        }
        $scope.page.page = $scope.page.page - 1;
        $scope.listUser();
    }
});


var loginApp = angular.module("loginApp",[],function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
});

loginApp.controller("LoginController",function($http,$scope,$location,$window){
    $scope.message = {"respMessage":"","showMessage":false};
    $scope.userInfo = {"username":"","password":""};

    $scope.login = function(){
        var data = "username=" + $scope.userInfo.username
                + "&password=" + $scope.userInfo.password;
        $http.post("/user/login",data).success(function(response){
            var result = response;
            if(result.success){
                var landingUrl = "http://" + $window.location.host + "/index";
                $window.open(landingUrl,"_self");
            } else {
                $scope.message.showMessage = true;
                $scope.message.respMessage = result.message;
            }
        });
    }

});