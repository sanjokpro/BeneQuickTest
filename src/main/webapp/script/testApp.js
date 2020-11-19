angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {

            fetchData();

            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                    console.log(response.data);
                    $scope.states = Object.keys(response.data);
                    $scope.raw=response.data;
                    $scope.refinedData=[];
                    angular.forEach(response.data, function (value, key) {
                        var obj={};
                        obj.key=key;
                        obj.value=value;
                        $scope.refinedData.push(obj);
                       // console.log();
                       // console.log("key:"+key+"value :"+value);
                    });
                    $scope.stateWiseMembers = response.data;
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }]);
