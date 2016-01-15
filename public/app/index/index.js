/**
 * Created by cassiano on 15/01/16.
 */
var index = angular.module('app.index',[]);

index.controller('IndexController', [ '$scope', 'Session',
    function($scope, Session){

        $scope.session = Session.get();

        $scope.isLogged = function(){
            return $scope.session.senha;
        };

    }]);