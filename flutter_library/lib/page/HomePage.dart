import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new HomePageState();
  }
}

class HomePageState extends State<HomePage> {
  /// 表示请求状态，防止频繁请求
  var _isSuccess = false;

  var listData;
  ScrollController _controller = new ScrollController();

  static const MethodChannel methodChannel =
      MethodChannel('sample.flutter.io/pull_home');

  TextStyle titleTextStyle = new TextStyle(fontSize: 15.0);
  TextStyle subtitleStyle =
      new TextStyle(color: const Color(0xFFB5BDC0), fontSize: 12.0);

  String _json = '';

  Future<Null> _getDataJson() async {
    String jsonStr;
    try {
      if(_isSuccess) {
        return;
      }
      final String result = await methodChannel.invokeMethod('getDataJson');
      jsonStr = result;
      _isSuccess = jsonStr != '';
    } on PlatformException {
      jsonStr = '';
      _isSuccess = false;
    }
    setState(() {
      _json = jsonStr;
    });
  }

  Future<Null> _readJson() async {
    Map<String, dynamic> map = json.decode(_json);
    var msg = map['msg'];
    var _listData = msg['news']['data'];
    print(_listData.toString());
    setState(() {
      listData = _listData;
    });
  }

  Future<Null> _pullToRefresh() async {
    _getDataJson();
    return null;
  }

  @override
  Widget build(BuildContext context) {
    _getDataJson();
    _readJson();

    if (listData == null) {
      return new Material(
        child: new Center(
          child: new CircularProgressIndicator(),
        ),
      );
    } else {
      Widget listView = new ListView.builder(
        itemCount: listData.length * 2,
        itemBuilder: (context, i) => renderRow(i),
        controller: _controller,
      );
      return new Material(
        child: new RefreshIndicator(child: listView, onRefresh: _pullToRefresh),
      );
    }
  }

  Widget renderRow(i) {
    /*i -= 1;
    if (i.isOdd) {
      return new Divider(height: 1.0);
    }
    i = i ~/ 2;
    var itemData = listData[i];
    if (itemData is String && itemData == "complete") {
      return new CommonEndLine();
    }
    var titleRow = new Row(
      children: <Widget>[
        new Expanded(
          child: new Text(itemData['title'], style: titleTextStyle),
        )
      ],
    );
    return new InkWell(
      child: titleRow,
    );*/
    i -= 1;
    if (i.isOdd) {
      return new Divider(height: 1.0);
    }
    i = i ~/ 2;
    var itemData = listData[i];

    var titleRow = new Row(
      children: <Widget>[
        new Expanded(
          child: new Text(itemData['title'], style: titleTextStyle),
        )
      ],
    );
    var timeRow = new Row(
      children: <Widget>[
        new Padding(
          padding: const EdgeInsets.fromLTRB(0.0, 0.0, 0.0, 0.0),
          child: new Text(
            '发布日期: ' + itemData['timeStr'],
            style: subtitleStyle,
          ),
        ),
      ],
    );
    var thumbImgUrl = itemData['thumb'];
    var thumbImg = new Container(
      margin: const EdgeInsets.all(10.0),
      width: 60.0,
      height: 60.0,
      decoration: new BoxDecoration(
        shape: BoxShape.circle,
        color: const Color(0xFFECECEC),
        image: new DecorationImage(
            image: new ExactAssetImage('./images/img_load_fail.png'),
            fit: BoxFit.cover),
        border: new Border.all(
          color: const Color(0xFFECECEC),
          width: 2.0,
        ),
      ),
    );
    if (thumbImgUrl != null && thumbImgUrl.length > 0) {
      thumbImg = new Container(
        margin: const EdgeInsets.all(10.0),
        width: 60.0,
        height: 60.0,
        decoration: new BoxDecoration(
          shape: BoxShape.circle,
          color: const Color(0xFFECECEC),
          image: new DecorationImage(
              image: new NetworkImage(thumbImgUrl), fit: BoxFit.cover),
          border: new Border.all(
            color: const Color(0xFFECECEC),
            width: 2.0,
          ),
        ),
      );
    }
    var row = new Row(
      children: <Widget>[
        new Padding(
          padding: const EdgeInsets.all(6.0),
          child: new Container(
            width: 100.0,
            height: 80.0,
            color: const Color(0xFFECECEC),
            child: new Center(
              child: thumbImg,
            ),
          ),
        ),
        new Expanded(
          flex: 1,
          child: new Padding(
            padding: const EdgeInsets.all(10.0),
            child: new Column(
              children: <Widget>[
                titleRow,
                new Padding(
                  padding: const EdgeInsets.fromLTRB(0.0, 8.0, 0.0, 0.0),
                  child: timeRow,
                )
              ],
            ),
          ),
        )
      ],
    );
    return new InkWell(
      child: row,
      onTap: () {
        /*Navigator.of(context).push(new MaterialPageRoute(
            builder: (ctx) => new NewsDetailPage(id: itemData['detailUrl'])
        ));*/
      },
    );
  }
}
