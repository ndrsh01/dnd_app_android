# DnD App - Android Version

Приложение для игроков Dungeons & Dragons 5e, содержащее справочники по заклинаниям, умениям, цитаты табакси и заметки.

## Функции

- **Заклинания D&D 5e** - полный справочник заклинаний с поиском и детальным просмотром
- **Умения (Feats)** - справочник умений персонажей с поиском по названию и категории
- **Цитаты табакси** - забавные цитаты от кошачьих персонажей с кнопкой случайной цитаты
- **Заметки** - создание, редактирование и удаление заметок для кампаний
- **Настройки** - выбор темы приложения и информация о приложении

## Технологии

- Kotlin
- Android SDK
- Material Design 3
- Navigation Component
- RecyclerView
- ViewBinding

## Установка и запуск

### Предварительные требования

1. **Android Studio** - последняя версия
2. **Java 8** или выше
3. **Android SDK** - API level 24+

### Установка

1. Клонируйте репозиторий:
```bash
git clone <repository-url>
cd dnd_app_android
```

2. Откройте проект в Android Studio

3. Синхронизируйте Gradle файлы

4. Подключите Android устройство или запустите эмулятор

5. Запустите приложение:
   - Нажмите "Run" в Android Studio, или
   - Выполните команду: `./gradlew installDebug`

### Проблемы с Gradle

Если возникают проблемы с Gradle wrapper:

1. Убедитесь, что у вас установлен Gradle
2. Или скачайте gradle-wrapper.jar и gradlew файлы
3. Или используйте Android Studio для автоматической настройки

## Структура проекта

```
app/src/main/
├── java/com/example/dndapp/
│   ├── MainActivity.kt          # Главная активность
│   ├── Models.kt               # Модели данных
│   ├── SpellsFragment.kt       # Фрагмент заклинаний
│   ├── SpellsAdapter.kt        # Адаптер для списка заклинаний
│   ├── FeatsFragment.kt        # Фрагмент умений
│   ├── FeatsAdapter.kt         # Адаптер для списка умений
│   ├── QuotesFragment.kt       # Фрагмент цитат
│   ├── NotesFragment.kt        # Фрагмент заметок
│   ├── NotesAdapter.kt         # Адаптер для списка заметок
│   └── SettingsFragment.kt     # Фрагмент настроек
├── res/
│   ├── layout/                 # Layout файлы
│   ├── drawable/               # Иконки и изображения
│   ├── values/                 # Строки, цвета, темы
│   ├── navigation/             # Навигация
│   └── menu/                   # Меню
└── AndroidManifest.xml
```


