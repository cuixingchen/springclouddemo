/**
 * Created by cuipengfei on 17-6-28.
 */
var mongo=new Mongo("127.0.0.1:27017");
var db=mongo.getDB("springclouddemo");
db.user.insert({ userId: 10, userName: "cuipengfei"});
db.user.insert({ userId: 10, userName: "小明"});