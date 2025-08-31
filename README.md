# BlockTraveller Testing Nexus

BT-testing-nexus 是一个专为 [BlockTraveller项目](https://github.com/xavier72bit/BlockTraveller) 设计的中心化测试控制平台。

* 为什么需要它？

    * 去中心化系统，其核心魅力在于没有中央控制点，所有参与者独立运行、相互协作。
    * 但这种自由也带来了巨大的挑战：由于缺乏全局视图和统一日志，我们很难直观地观察、调试和验证整个网络的状态和行为。在开发和测试 BlockTraveller 项目时，仅仅通过脚本实例化角色进行测试，无法有效模拟和验证节点间复杂的同步、共识和交易流转等行为。

* 核心思想
    * 用我们熟知的中心化工具，来辅助我们理解和验证陌生的去中心化世界。
    * 我们利用一个中心化的 Web 应用 和 数据库，构建一个可视化的控制台。这个控制台通过暴露给 BlockTraveller 节点的 Debug API，实现了对去中心化网络的全局监控和精确控制。

简而言之，BT-testing-nexus 是连接中心化开发工具与去中心化应用行为的桥梁，它极大地提升了 BlockTraveller 项目的开发和测试效率。

# 系统架构

前后端分离架构

后端SpringBoot框架 + 前端Vue3框架
