/**
 * Original code was created by Minecraft Forge Team.
 */

package mods.defeatedcrow.asm.config;

import static mods.defeatedcrow.asm.config.DCsConfiguration.NEW_LINE;
import static mods.defeatedcrow.asm.config.DCsConfiguration.allowedProperties;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ConfigCategoryDC implements Map<String, PropertyDC> {
	private String name;
	private String comment;
	private ArrayList<ConfigCategoryDC> children = new ArrayList<ConfigCategoryDC>();
	private Map<String, PropertyDC> properties = new TreeMap<String, PropertyDC>();
	public final ConfigCategoryDC parent;
	private boolean changed = false;

	public ConfigCategoryDC(String name) {
		this(name, null);
	}

	public ConfigCategoryDC(String name, ConfigCategoryDC parent) {
		this.name = name;
		this.parent = parent;
		if (parent != null) {
			parent.children.add(this);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConfigCategoryDC) {
			ConfigCategoryDC cat = (ConfigCategoryDC) obj;
			return name.equals(cat.name) && children.equals(cat.children);
		}

		return false;
	}

	public String getQualifiedName() {
		return getQualifiedName(name, parent);
	}

	public static String getQualifiedName(String name, ConfigCategoryDC parent) {
		return (parent == null ? name : parent.getQualifiedName() + DCsConfiguration.CATEGORY_SPLITTER + name);
	}

	public ConfigCategoryDC getFirstParent() {
		return (parent == null ? this : parent.getFirstParent());
	}

	public boolean isChild() {
		return parent != null;
	}

	public Map<String, PropertyDC> getValues() {
		return ImmutableMap.copyOf(properties);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public PropertyDC get(String key) {
		return properties.get(key);
	}

	private void write(BufferedWriter out, String... data) throws IOException {
		write(out, true, data);
	}

	private void write(BufferedWriter out, boolean new_line, String... data) throws IOException {
		for (int x = 0; x < data.length; x++) {
			out.write(data[x]);
		}
		if (new_line)
			out.write(NEW_LINE);
	}

	public void write(BufferedWriter out, int indent) throws IOException {
		String pad0 = getIndent(indent);
		String pad1 = getIndent(indent + 1);
		String pad2 = getIndent(indent + 2);

		write(out, pad0, "####################");
		write(out, pad0, "# ", name);

		if (comment != null) {
			write(out, pad0, "#===================");
			Splitter splitter = Splitter.onPattern("\r?\n");

			for (String line : splitter.split(comment)) {
				write(out, pad0, "# ", line);
			}
		}

		write(out, pad0, "####################", NEW_LINE);

		if (!allowedProperties.matchesAllOf(name)) {
			name = '"' + name + '"';
		}

		write(out, pad0, name, " {");

		PropertyDC[] props = properties.values().toArray(new PropertyDC[properties.size()]);

		for (int x = 0; x < props.length; x++) {
			PropertyDC prop = props[x];

			if (prop.comment != null) {
				if (x != 0) {
					out.newLine();
				}

				Splitter splitter = Splitter.onPattern("\r?\n");
				for (String commentLine : splitter.split(prop.comment)) {
					write(out, pad1, "# ", commentLine);
				}
			}

			String propName = prop.getName();

			if (!allowedProperties.matchesAllOf(propName)) {
				propName = '"' + propName + '"';
			}

			if (prop.isList()) {
				char type = prop.getType().getID();

				write(out, pad1, String.valueOf(type), ":", propName, " <");

				for (String line : prop.getStringList()) {
					write(out, pad2, line);
				}

				write(out, pad1, " >");
			} else if (prop.getType() == null) {
				write(out, pad1, propName, "=", prop.getString());
			} else {
				char type = prop.getType().getID();
				write(out, pad1, String.valueOf(type), ":", propName, "=", prop.getString());
			}
		}

		for (ConfigCategoryDC child : children) {
			child.write(out, indent + 1);
		}

		write(out, pad0, "}", NEW_LINE);
	}

	private String getIndent(int indent) {
		StringBuilder buf = new StringBuilder("");
		for (int x = 0; x < indent; x++) {
			buf.append("    ");
		}
		return buf.toString();
	}

	public boolean hasChanged() {
		if (changed)
			return true;
		for (PropertyDC prop : properties.values()) {
			if (prop.hasChanged())
				return true;
		}
		return false;
	}

	void resetChangedState() {
		changed = false;
		for (PropertyDC prop : properties.values()) {
			prop.resetChangedState();
		}
	}

	// Map bouncer functions for compatibility with older mods, to be removed once all mods stop using it.
	@Override
	public int size() {
		return properties.size();
	}

	@Override
	public boolean isEmpty() {
		return properties.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return properties.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return properties.containsValue(value);
	}

	@Override
	public PropertyDC get(Object key) {
		return properties.get(key);
	}

	@Override
	public PropertyDC put(String key, PropertyDC value) {
		changed = true;
		return properties.put(key, value);
	}

	@Override
	public PropertyDC remove(Object key) {
		changed = true;
		return properties.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends PropertyDC> m) {
		changed = true;
		properties.putAll(m);
	}

	@Override
	public void clear() {
		changed = true;
		properties.clear();
	}

	@Override
	public Set<String> keySet() {
		return properties.keySet();
	}

	@Override
	public Collection<PropertyDC> values() {
		return properties.values();
	}

	@Override
	// Immutable copy, changes will NOT be reflected in this category
	public Set<java.util.Map.Entry<String, PropertyDC>> entrySet() {
		return ImmutableSet.copyOf(properties.entrySet());
	}

	public Set<ConfigCategoryDC> getChildren() {
		return ImmutableSet.copyOf(children);
	}

	public void removeChild(ConfigCategoryDC child) {
		if (children.contains(child)) {
			children.remove(child);
			changed = true;
		}
	}
}
