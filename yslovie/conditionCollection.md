## Мини-проект. NoteService

Давайте от постов перейдём к другой сущности нашего приложения — [Заметкам](https://dev.vk.com/method/notes). Есть [сохранённая копия](assets/Notes.pdf).

Вам нужно реализовать все методы, которые указаны. При этом вы можете опустить часть полей (например, `guid` и `owner_id`) и аргументов функций (например, `owner_id` и `reply_to`).

Если вы внимательно изучите документацию на все методы, то увидите, что почти все сервисы реализуют одну и ту же функциональность: CRUD (Create, Read, Update, Delete).

Научившись, вы сможете сделать примерно 70 % окружающих вас систем.

В этот раз вы должны использовать Generic и коллекции.

Обратите внимание: система позволяет восстанавливать удалённые комментарии.

Это значит, что комментарии на самом деле не удаляются. Вам нужно придумать, как это реализовать.

<details>
<summary>Подсказка</summary>

Один из простых вариантов — это ставить в любом объекте пометку удалён или нет. Но тогда её нужно везде учитывать: например, нельзя редактировать удалённый комментарий или отображать в списке комментариев.

</details>

Вам может показаться, что задача большая. Но вы увидите, что код работы с заметками лишь немного отличается от кода работы с комментариями: у последнего будут проверки на существования родительского объекта (заметки).

Из ловушек
* Что делать, если удаляется заметка (удалять ли комментарии)?
* Что делать, если пользователь пытается удалить (или отредактировать) уже удалённый комментарий?
* Что делать, если пользователь пытается восстановить удалённый комментарий?

Вариантов на последние два пункта всего два: либо ничего, либо выкидывать Exception. Подумайте и реализуйте одну из стратегий.

Итог: у вас должен быть репозиторий на GitHub, в котором расположен ваш Gradle-проект. Автотесты также должны храниться в репозитории.
