Test comparing Jackson and Kotlin DataFrame read and write to CSV

The generated schema which doesn't have lists

~~~
@DataSchema
interface ReferenceRecord {
    val id: kotlin.String
    val list1: kotlin.collections.List<kotlin.String>?
    val list2: kotlin.collections.List<kotlin.String>?
    val list3: kotlin.collections.List<kotlin.String>?
}

val org.jetbrains.kotlinx.dataframe.ColumnsContainer<ReferenceRecord>.id: org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.String> @JvmName("ReferenceRecord_id") get() = this["id"] as org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.String>
val org.jetbrains.kotlinx.dataframe.DataRow<ReferenceRecord>.id: kotlin.String @JvmName("ReferenceRecord_id") get() = this["id"] as kotlin.String
val org.jetbrains.kotlinx.dataframe.ColumnsContainer<ReferenceRecord>.list1: org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?> @JvmName("ReferenceRecord_list1") get() = this["list1"] as org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?>
val org.jetbrains.kotlinx.dataframe.DataRow<ReferenceRecord>.list1: kotlin.collections.List<kotlin.String>? @JvmName("ReferenceRecord_list1") get() = this["list1"] as kotlin.collections.List<kotlin.String>?
val org.jetbrains.kotlinx.dataframe.ColumnsContainer<ReferenceRecord>.list2: org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?> @JvmName("ReferenceRecord_list2") get() = this["list2"] as org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?>
val org.jetbrains.kotlinx.dataframe.DataRow<ReferenceRecord>.list2: kotlin.collections.List<kotlin.String>? @JvmName("ReferenceRecord_list2") get() = this["list2"] as kotlin.collections.List<kotlin.String>?
val org.jetbrains.kotlinx.dataframe.ColumnsContainer<ReferenceRecord>.list3: org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?> @JvmName("ReferenceRecord_list3") get() = this["list3"] as org.jetbrains.kotlinx.dataframe.DataColumn<kotlin.collections.List<kotlin.String>?>
val org.jetbrains.kotlinx.dataframe.DataRow<ReferenceRecord>.list3: kotlin.collections.List<kotlin.String>? @JvmName("ReferenceRecord_list3") get() = this["list3"] as kotlin.collections.List<kotlin.String>?
~~~
