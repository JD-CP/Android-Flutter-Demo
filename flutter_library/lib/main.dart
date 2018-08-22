import 'dart:async';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_library/page/HomePage.dart';
import 'package:flutter_library/page/DashboardPage.dart';
import 'package:flutter_library/page/NotificationPage.dart';

void main() => runApp(new MyApp(window.defaultRouteName));

class MyApp extends StatelessWidget {
  final String route;

  MyApp(this.route);

  @override
  Widget build(BuildContext context) {
    switch (route) {
      case "app/home":
        return new MaterialApp(
          home: new HomePage(),
        );
        break;
      case "app/dashboard":
        return new MaterialApp(
          home: new DashboardPage(),
        );
        break;
      case "app/notification":
        return new MaterialApp(
          home: new NotificationPage(),
        );
        break;
      default:
        return Center(
          child:
              Text('Unknown route: $route', textDirection: TextDirection.ltr),
        );
    }
  }
}
