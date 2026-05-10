# WelcomePlayer

一个轻量级的 Minecraft 服务器欢迎消息插件，支持 Folia 多线程架构。

## ✨ 功能特性

- 🎯 **多条消息支持** - 可配置多条欢迎消息，按顺序发送
- 🌈 **颜色代码支持** - 支持 `&` 符号的颜色和格式代码
- 👤 **玩家占位符** - 使用 `%player_name%` 显示玩家名字
- ⏱️ **延迟发送** - 可配置消息发送延迟时间
- 🔧 **灵活配置** - 可选择仅在首次加入时发送
- 🚀 **Folia 兼容** - 完全支持 Folia 多线程服务端
- 📦 **轻量级** - 无依赖，开箱即用

## 📋 要求

- Java 21 或更高版本
- Paper 1.21+ 或 Folia 1.21+ 服务端

## 🚀 安装

1. 从 [Releases](https://github.com/2698269088/WelcomePlayer/releases) 下载最新版本的 JAR 文件
2. 将 JAR 文件放入服务器的 `plugins` 文件夹
3. 重启服务器
4. 编辑生成的 `config.yml` 文件自定义欢迎消息

## ⚙️ 配置说明

### config.yml

```yaml
# 是否启用欢迎消息功能
enabled: true

# 欢迎消息列表（支持多条消息，按顺序发送）
# 支持颜色代码和格式代码
# 使用 & 符号后跟颜色/格式代码，例如: &a表示绿色, &l表示粗体
messages:
  - "&e================================"
  - "&a欢迎 &6%player_name% &a加入服务器！"
  - "&b祝你游戏愉快！"
  - "&e================================"

# 是否在发送消息前延迟（单位：秒，0表示立即发送）
delay: 1

# 是否只在玩家首次加入时发送欢迎消息
first-join-only: false
```

### 配置项说明

| 配置项 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `enabled` | boolean | `true` | 是否启用欢迎消息功能 |
| `messages` | list | - | 欢迎消息列表，支持多条 |
| `delay` | int | `0` | 发送延迟（秒），0 表示立即发送 |
| `first-join-only` | boolean | `false` | 是否仅在玩家首次加入时发送 |

### 颜色代码

插件支持所有 Minecraft 颜色代码和格式代码：

- `&0` - `&9`: 颜色代码（黑色到蓝色）
- `&a` - `&f`: 颜色代码（绿色到白色）
- `&k`: 随机字符
- `&l`: **粗体**
- `&m`: ~~删除线~~
- `&n`: <u>下划线</u>
- `&o`: *斜体*
- `&r`: 重置格式

### 占位符

- `%player_name%`: 玩家的名字

## 🛠️ 构建

如果你想自己构建这个插件：

```bash
# 克隆仓库
git clone https://github.com/2698269088/WelcomePlayer.git
cd WelcomePlayer

# 构建项目
mvn clean package
```

构建完成后，JAR 文件将位于 `target/` 目录中。

## 📖 使用示例

### 基础欢迎消息

```yaml
messages:
  - "&a欢迎加入服务器！"
```

### 多条消息带延迟

```yaml
messages:
  - "&e================================"
  - "&a欢迎 &6%player_name% &a加入服务器！"
  - "&b当前在线人数: &e%online%"
  - "&e================================"
delay: 2
```

### 仅首次加入时发送

```yaml
first-join-only: true
messages:
  - "&a欢迎来到我们的服务器！"
  - "&b这是你第一次加入，祝你游戏愉快！"
```

## 🐛 问题反馈

如果你遇到了问题或有功能建议，请在 [Issues](https://github.com/2698269088/WelcomePlayer/issues) 中提交。

## 📄 开源协议

本项目采用 MIT 协议开源。详见 [LICENSE](LICENSE) 文件。

## 👨‍💻 作者

- **MCOCET** - [GitHub](https://github.com/2698269088)

## 🙏 致谢

感谢以下项目和组织：

- [PaperMC](https://papermc.io/) - 提供强大的服务端 API
- [Folia](https://papermc.io/software/folia) - 多线程服务端支持
- [Minecraft](https://www.minecraft.net/) - 伟大的游戏

---

**享受你的服务器之旅！** 🎮
