# Simple Anti-Relog Plugin

## Overview
Simple Anti-Relog is a Minecraft plugin that helps server admins prevent players from logging out during combat. It is lightweight, easy to customize, and supports Minecraft versions from 1.13 to 1.21.x.

## Features
- **Combat Timer**: Ensures players can't log out to avoid combat.
- **Bypass Permission**: Allow specific players to bypass the anti-relog rules.
- **Multi-Language Support**: Supports multiple languages for wider usability.
- ~~**Configurable Commands**: Block specific commands during combat (e.g., `/spawn`, `/home`).~~
- **Projectiles Support**: Triggers anti-relog when hit by projectiles.

## Installation
1. Download the plugin .jar file from [SpigotMC](https://www.spigotmc.org/resources/simple-anti-relog-1-13-1-21-x.94869).
2. Place the .jar file into your server's `plugins` folder.
3. Restart your server or reload the plugin.

## Configuration
Edit the `config.yml` file to customize the plugin's behavior. You can set:
- Combat duration
- Bypass permissions
- Custom death messages

## Permissions
- `antirelog.bypass`: Allows players to bypass the anti-relog restrictions.

## Changelog
- **v1.4.2**: Added bypass for specific players, projectile support, and bug fixes.

For more details, visit the [official plugin page](https://www.spigotmc.org/resources/simple-anti-relog-1-13-1-21-x.94869).
