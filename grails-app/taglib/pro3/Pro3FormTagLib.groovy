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

    /**
     * Tag for creating a checkBox 
     *
     * @attr readonly Defaults to false
     */
    Closure checkBox = { attrs ->
        checkBox(out, attrs)
    }

    @CompileStatic
    def field(GrailsPrintWriter out, Map attrs) {
        attrs.tagName = "field"
        boolean readonly = Boolean.valueOf(attrs.remove('readonly').toString()) ?: false
        if (readonly) {
            String readOnlyStr = attrs.value != null ? attrs.value.toString() : ''
            out << readOnlyStr
        } else {
            fieldImpl(out, attrs)
        }
    }

    @CompileStatic
    def checkBox(GrailsPrintWriter out, Map attrs) {
        boolean readonly = Boolean.valueOf(attrs.remove('readonly').toString()) ?: false
        if (readonly) {
            String readOnlyStr = attrs.value != null ? attrs.value.toString() : ''
            out << readOnlyStr
        } else {
            checkBox(out, attrs)
        }
    }
    /**
     * Tag for creating a label and field 
     *
     * @attr label REQUIRED the label of the field
     * @attr readonly Defaults to false
     * @attr tooltip Do we want to display a tool tip here 
     * @attr required Is this field required
     */
    Closure labelfield = { attrs ->
        def label = attrs.remove('label')
        def name = attrs.get('name')
        def tooltip = attrs.remove('tooltip')
        boolean required = Boolean.valueOf(attrs.remove('required').toString()) ?: false
        boolean readonly = Boolean.valueOf(attrs.get('readonly').toString()) ?: false
        
        if (tooltip == null) {
            if (!required || readonly) {
                out << "<label for=\"${name}\">${label}</label>"
            } else {
                out << "<label for=\"${name}\">${label} " +
                        "<i class=\"fa fa-star fa-1\" aria-hidden=\"true\"></i>" +
                        "</label>"
            }
        } else {
            if (!required || readonly) {
                out << "<label title=\"${tooltip}\" for=\"${name}\">${label} <i class=\"fa fa-info-circle fa-1\" aria-hidden=\"true\"></i></label>"
            } else {
                out << "<label title=\"${tooltip}\" for=\"${name}\">${label} " +
                        "<i class=\"fa fa-star fa-1\" aria-hidden=\"true\"></i> " +
                        "<i class=\"fa fa-info-circle fa-1\" aria-hidden=\"true\"></i>" +
                        "</label>"
            }
        }
        field(out, attrs)
    }

}
