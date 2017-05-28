package pro3

import groovy.transform.CompileStatic
import org.grails.buffer.GrailsPrintWriter
import org.grails.plugins.web.taglib.FormTagLib

class Pro3FormTagLib extends FormTagLib{
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = 'pro3'


    /**
     * Tag for creating a field 
     *
     * @attr readonly Defaults to false
     */
    Closure field = { attrs ->
        field(out, attrs)
    }

    @CompileStatic
    def field(GrailsPrintWriter out, Map attrs) {
        attrs.tagName = "field"
        boolean readonly = Boolean.valueOf(attrs.remove('readonly').toString()) ?: false
        if (readonly) {
            out << attrs.value.toString()
        } else {
            fieldImpl(out, attrs)
        }
    }

        /**
     * Tag for creating a label and field 
     *
     * @attr label REQUIRED the label of the field
     * @attr readonly Defaults to false
     */
    Closure labelfield = { attrs ->
/*
        <label for="budget">Budget</label>
*/      
        def label = attrs.remove('label')
        def name = attrs.remove('name')
        out << "<label for=\"${name}\">${label}</label>"
        field(out, attrs)
    }

}
